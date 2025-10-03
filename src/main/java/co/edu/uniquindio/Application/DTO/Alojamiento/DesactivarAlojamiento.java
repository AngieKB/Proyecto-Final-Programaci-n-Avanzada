package co.edu.uniquindio.Application.DTO.Alojamiento;

import jakarta.validation.constraints.NotNull;

public record DesactivarAlojamiento(
        @NotNull AlojamientoDTO alojamiento
        ) {
}
