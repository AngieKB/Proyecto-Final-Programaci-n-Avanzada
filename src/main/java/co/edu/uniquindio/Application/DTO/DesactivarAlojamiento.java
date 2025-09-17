package co.edu.uniquindio.Application.DTO;

import co.edu.uniquindio.Application.Model.Alojamiento;
import jakarta.validation.constraints.NotNull;

public record DesactivarAlojamiento(
        @NotNull AlojamientoDTO alojamiento
        ) {
}
