package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.Comentario.ComentarDTO;
import co.edu.uniquindio.Application.DTO.Comentario.ComentarioDTO;
import co.edu.uniquindio.Application.DTO.EmailDTO;
import co.edu.uniquindio.Application.Model.Alojamiento;
import co.edu.uniquindio.Application.Model.Comentario;
import co.edu.uniquindio.Application.Model.EstadoReserva;
import co.edu.uniquindio.Application.Model.Reserva;
import co.edu.uniquindio.Application.Repository.AlojamientoRepository;
import co.edu.uniquindio.Application.Repository.ComentarioRepository;
import co.edu.uniquindio.Application.Repository.ReservaRepository;
import co.edu.uniquindio.Application.Services.ComentarioService;
import co.edu.uniquindio.Application.Mappers.ComentarioMapper;
import co.edu.uniquindio.Application.Services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;
    private final ReservaRepository reservaRepository;
    private final AlojamientoRepository alojamientoRepository;
    private final EmailService emailService;


    @Override
    public void comentar(Long reservaId, ComentarDTO comentarDTO) throws Exception {
        // 1. Buscar la reserva
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new Exception("La reserva no existe"));

        // 2. Validar que pertenezca al huésped y al alojamiento correctos
        if (!reserva.getHuesped().getId().equals(comentarDTO.idUsuario()) ||
                !reserva.getAlojamiento().getId().equals(comentarDTO.idAlojamiento())) {
            throw new Exception("La reserva no corresponde al usuario o al alojamiento indicado");
        }

        // 3. Validar que la reserva esté completada
        if (reserva.getFechaCheckOut().isAfter(LocalDateTime.now())) {
            throw new Exception("No puede comentar: la reserva aún no ha finalizado");
        }

        // 4. Validar que no exista ya comentario para esa reserva
        if (comentarioRepository.existsByReservaId(reservaId)) {
            throw new Exception("Ya ha comentado esta reserva");
        }

        // 5. Crear y guardar comentario
        Comentario comentario = comentarioMapper.toEntity(comentarDTO);
        comentario.setReserva(reserva);
        comentarioRepository.save(comentario);

        // 6. Actualizar promedio de calificación del alojamiento
        Alojamiento alojamiento = reserva.getAlojamiento();

        double promedio = comentarioRepository.findByAlojamientoId(alojamiento.getId())
                .stream()
                .mapToInt(Comentario::getCalificacion)
                .average()
                .orElse(0);

        alojamiento.setCalificacionPromedio(promedio);
        alojamientoRepository.save(alojamiento);
        emailService.sendMail(
                new EmailDTO("Nuevo comentario en: " + alojamiento.getTitulo(), "Un usuario realizó un comentario en tu alojamiento", alojamiento.getAnfitrion().getUsuario().getEmail())
        );
    }



    public List<ComentarioDTO> listarComentariosPorAlojamiento(Long alojamientoId) throws Exception {
        return comentarioRepository.findByAlojamientoIdOrderByFechaDesc(alojamientoId)
                .stream()
                .map(comentarioMapper::toDto)
                .toList();
    }

}
