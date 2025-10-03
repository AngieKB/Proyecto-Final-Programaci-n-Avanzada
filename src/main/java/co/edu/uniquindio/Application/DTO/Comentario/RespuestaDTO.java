package co.edu.uniquindio.Application.DTO.Comentario;

import java.time.LocalDateTime;

public record RespuestaDTO (
        Long id,
        Long comentarioId,
        String texto,
        LocalDateTime fecha
        //UsuarioDTO autor
) {
}