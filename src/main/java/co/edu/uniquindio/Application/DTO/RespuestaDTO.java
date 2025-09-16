package co.edu.uniquindio.Application.DTO;

import java.time.LocalDateTime;

public record RespuestaDTO (
        Long id,
        String texto,
        LocalDateTime fecha,
        Anfitrion autor
) {
}