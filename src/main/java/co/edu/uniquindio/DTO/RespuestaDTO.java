package co.edu.uniquindio.DTO;

import co.edu.uniquindio.Model.Anfitrion;

import java.time.LocalDateTime;

public record RespuestaDTO (
        Long id,
        String texto,
        LocalDateTime fecha,
        Anfitrion autor
) {
}