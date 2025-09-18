package co.edu.uniquindio.Application.DTO;

import java.time.LocalDateTime;

public record ComentarioDTO (
        Long id,
        Long usuarioId,
        Long alojamientoId,
        Long reservaId,
        String texto,
        Integer calificacion,
        LocalDateTime fecha,
        RespuestaDTO respuesta
) {}