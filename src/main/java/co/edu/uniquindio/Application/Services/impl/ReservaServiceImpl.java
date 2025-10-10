package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.EmailDTO;
import co.edu.uniquindio.Application.DTO.Reserva.RealizarReservaDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaAlojamientoDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaUsuarioDTO;
import co.edu.uniquindio.Application.Exceptions.BadCredentialsException;
import co.edu.uniquindio.Application.Model.Alojamiento;
import co.edu.uniquindio.Application.Model.EstadoReserva;
import co.edu.uniquindio.Application.Model.Reserva;
import co.edu.uniquindio.Application.Model.Usuario;
import co.edu.uniquindio.Application.Repository.AlojamientoRepository;
import co.edu.uniquindio.Application.Repository.ReservaRepository;
import co.edu.uniquindio.Application.Repository.UsuarioRepository;
import co.edu.uniquindio.Application.Services.EmailService;
import co.edu.uniquindio.Application.Services.ReservaService;
import co.edu.uniquindio.Application.Mappers.ReservaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservaServiceImpl implements ReservaService {
    private final ReservaMapper reservaMapper;
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final AlojamientoRepository alojamientoRepository;
    private final EmailService emailService;

    @Override
    public void cancelarReserva(Long id)  {
        reservaRepository.findById(id).ifPresentOrElse(reserva -> {
            if(reserva.getEstado() == EstadoReserva.CANCELADA) {
                throw new RuntimeException("La reserva ya se encuentra cancelada.");
            }
            LocalDateTime ahora = LocalDateTime.now();
            if(reserva.getFechaCheckIn().minusHours(48).isBefore(ahora)) {
                throw new RuntimeException("No se puede cancelar la reserva a menos de 48 horas del check-in.");
            }
            reserva.setEstado(EstadoReserva.CANCELADA);
            reservaRepository.save(reserva);
            try {
                emailService.sendMail(
                        new EmailDTO("Cancelación de: " + reserva.getHuesped().getNombre(),
                                "El usuario " + reserva.getHuesped().getNombre() + "canceló su reserva que estaba registrada para el día " + reserva.getFechaCheckIn() + "en el alojamiento " + reserva.getAlojamiento().getTitulo(), reserva.getAlojamiento().getAnfitrion().getUsuario().getEmail())
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }, () -> {
            throw new RuntimeException("No existe una reserva con el id " + id);
        });
    }

    @Override
    public EstadoReserva obtenerEstadoReserva(Long id) {
        return null;
    }

    @Override
    public void editarReserva(Long id, ReservaDTO dto){}

    @Override
    public List<ReservaUsuarioDTO> obtenerReservasPorIdHuesped(Long id) {
        List<Reserva> reservas = reservaRepository.findByHuespedId(id);

        // Ordenar de más reciente a más antigua
        reservas.sort((r1, r2) -> r2.getFechaCheckIn().compareTo(r1.getFechaCheckIn()));

        return reservas.stream()
                .map(reservaMapper::toUsuarioDTO)
                .toList();
    }

    @Override
    public List<ReservaAlojamientoDTO> obtenerReservasPorIdAlojamiento(Long id) {
        List<Reserva> reservas = reservaRepository.findByAlojamientoId(id);

        // Ordenar de más reciente a más antigua
        reservas.sort((r1, r2) -> r2.getFechaCheckIn().compareTo(r1.getFechaCheckIn()));

        return reservas.stream()
                .map(reservaMapper::toAlojamientoDTO)
                .toList();
    }
    @Override
    public void guardar(RealizarReservaDTO reservadto) throws Exception {

        // convertir ids en entidades
        Usuario huesped = usuarioRepository.findById(reservadto.huespedId())
                .orElseThrow(() -> new RuntimeException("No existe huésped con id " + reservadto.huespedId()));
        Alojamiento alojamiento = alojamientoRepository.findById(reservadto.alojamientoId())
                .orElseThrow(() -> new RuntimeException("No existe alojamiento con id " + reservadto.alojamientoId()));
        if(reservadto.fechaCheckIn().isBefore(LocalDateTime.now()) || reservadto.fechaCheckOut().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("No se pueden reservar fechas pasadas.");
        }
        //  Validar mínimo 1 noche
        if(Duration.between(reservadto.fechaCheckIn(), reservadto.fechaCheckOut()).toDays() < 1) {
            throw new RuntimeException("La reserva debe ser mínimo de 1 noche.");
        }
        if(reservadto.cantidadHuespedes() > alojamiento.getCapacidadMax()) {
            throw new RuntimeException("Se supera la capacidad máxima del alojamiento.");
        }
        Reserva newReserva = reservaMapper.toEntity(reservadto);
        newReserva.setEstado(EstadoReserva.PENDIENTE);
        newReserva.setHuesped(huesped);
        newReserva.setAlojamiento(alojamiento);

        Double valorTotal = calcularValorTotal(newReserva);
        newReserva.setTotal(valorTotal);

        reservaRepository.save(newReserva);

        emailService.sendMail(
                new EmailDTO("Reserva en appBooking de: " + newReserva.getHuesped().getNombre(),
                        "Su reserva se realizó satisfactoriamente con los siguientes datos. \\nDía de llegada: " + newReserva.getFechaCheckIn()
                        + "\\nDía de salida: " + newReserva.getFechaCheckOut(),
                        newReserva.getHuesped().getEmail())
        );
        emailService.sendMail(
                new EmailDTO("Reserva en appBooking de: " + newReserva.getHuesped().getNombre(), "Un usuario realizó una reserva", newReserva.getAlojamiento().getAnfitrion().getUsuario().getEmail())
        );
    }
    @Override
    @Transactional
    public void actualizarReservasCompletadas() {
        int filasActualizadas = reservaRepository.actualizarReservasCompletadas(
                EstadoReserva.COMPLETADA,
                LocalDateTime.now(),
                List.of(EstadoReserva.CANCELADA, EstadoReserva.COMPLETADA)
        );
        System.out.println("Reservas completadas actualizadas: " + filasActualizadas);
    }

    public double calcularValorTotal(Reserva reserva){
        long dias = reserva.getFechaCheckOut().toLocalDate().toEpochDay() - reserva.getFechaCheckIn().toLocalDate().toEpochDay();
        return dias * reserva.getAlojamiento().getPrecioNoche()* reserva.getCantidadHuespedes();
    }

}

