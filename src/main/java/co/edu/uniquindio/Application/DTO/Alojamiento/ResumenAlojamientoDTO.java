package co.edu.uniquindio.Application.DTO.Alojamiento;

public record ResumenAlojamientoDTO(
        Long id,
        String titulo,
        String ciudad,
        Double precioNoche,
        Double calificacionPromedio,
        String imagenPrincipal
){}
