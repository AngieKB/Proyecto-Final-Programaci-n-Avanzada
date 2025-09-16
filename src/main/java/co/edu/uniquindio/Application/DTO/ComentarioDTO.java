package co.edu.uniquindio.Application.DTO;

import co.edu.uniquindio.Application.Model.Alojamiento;
import co.edu.uniquindio.Application.Model.Respuesta;

import java.time.LocalDateTime;

public record ComentarioDTO (
        Long id,
        String texto,
        Integer calificacion,
        LocalDateTime fecha,
        Respuesta respuesta,
        Huesped autor,
        Alojamiento alojamiento
) {}