package co.edu.uniquindio.Application.DTO.Comentario;

import java.time.LocalDateTime;

public record ComentarioDTO (
        Long id,
        String texto,
        Integer calificacion,
        LocalDateTime fecha
) {}