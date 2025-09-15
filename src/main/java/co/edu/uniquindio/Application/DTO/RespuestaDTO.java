package co.edu.uniquindio.Application.DTO;

import co.edu.uniquindio.Application.Model.Anfitrion;

import java.time.LocalDateTime;

public record RespuestaDTO (
        Long id,
        String texto,
        LocalDateTime fecha,
        Anfitrion autor
) {
}