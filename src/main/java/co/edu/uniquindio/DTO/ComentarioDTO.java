package co.edu.uniquindio.DTO;

import co.edu.uniquindio.Model.Alojamiento;
import co.edu.uniquindio.Model.Huesped;
import co.edu.uniquindio.Model.Respuesta;

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