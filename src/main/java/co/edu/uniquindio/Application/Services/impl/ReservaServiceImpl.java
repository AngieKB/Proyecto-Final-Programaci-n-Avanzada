package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.EmailDTO;
import co.edu.uniquindio.Application.DTO.Reserva.RealizarReservaDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaDTO;
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

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {
    private final ReservaMapper reservaMapper;
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final AlojamientoRepository alojamientoRepository;
    private final EmailService emailService;

    @Override
    public void cancelarReserva(Long id) {
    }
    @Override
    public EstadoReserva obtenerEstadoReserva(Long id) {
        return null;
    }

    @Override
    public void editarReserva(Long id, ReservaDTO dto){}

    @Override
    public ReservaDTO obtenerPorId(Long id) {
        return null;
    }
    @Override
    public void guardar(RealizarReservaDTO reservadto) throws Exception {
        Reserva newReserva = reservaMapper.toEntity(reservadto);
        newReserva.setEstado(EstadoReserva.PENDIENTE);

        // convertir ids en entidades
        Usuario huesped = usuarioRepository.findById(reservadto.huespedId())
                .orElseThrow(() -> new RuntimeException("No existe huésped con id " + reservadto.huespedId()));
        Alojamiento alojamiento = alojamientoRepository.findById(reservadto.alojamientoId())
                .orElseThrow(() -> new RuntimeException("No existe alojamiento con id " + reservadto.alojamientoId()));

        newReserva.setHuesped(huesped);
        newReserva.setAlojamiento(alojamiento);

        reservaRepository.save(newReserva);

        emailService.sendMail(
                new EmailDTO("Asunto", "Cuerpo mensaje", newReserva.getHuesped().getEmail())
        );
    }

}

