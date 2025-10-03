package co.edu.uniquindio.Application.DTO.Alojamiento;

import jakarta.validation.constraints.NotNull;

public record AgregarFavoritoDTO(
        @NotNull Long usuarioId,
        @NotNull Long alojamientoId
) {}