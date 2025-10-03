package co.edu.uniquindio.Application.DTO.Alojamiento;

import org.hibernate.validator.constraints.Length;

import java.util.List;

public record EditarAlojamientoDTO(
        @Length(max = 100) String titulo,
        @Length(max = 300) String descripcion,
        List<String> servicios,
        List<String> galeria,
        @Length(max = 20) String ciudad,
        @Length(max = 50) String direccion,
        Double latitud,
        Double longitud,
        Double precioNoche,
        Integer capacidadMax
) {}