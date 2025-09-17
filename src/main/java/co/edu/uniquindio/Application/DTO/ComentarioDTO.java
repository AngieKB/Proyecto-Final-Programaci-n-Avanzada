package co.edu.uniquindio.Application.DTO;

import java.time.LocalDateTime;

public record ComentarioDTO (
        Long id,
        Long usuarioId,
        Long alojamientoId,
        String texto,
        Integer calificacion,
        LocalDateTime fecha,
        RespuestaDTO respuesta
        //UsuarioDTO autor,
        //AlojamientoDTO alojamiento
) {}