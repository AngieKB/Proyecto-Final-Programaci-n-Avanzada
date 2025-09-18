package co.edu.uniquindio.Application.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioPasswordCambiarDTO(
    @NotNull Long usuarioId,
    @NotBlank String passwordActual,
    @NotBlank String passwordNueva
){}
