package co.edu.uniquindio.Application.DTO;

import co.edu.uniquindio.Application.Model.Alojamiento;
import jakarta.validation.constraints.NotNull;

public record AgregarFavoritoDTO(
        @NotNull Huesped huesped,
        @NotNull Alojamiento alojamiento
        ) {
}