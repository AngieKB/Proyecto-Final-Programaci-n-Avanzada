package co.edu.uniquindio.Application.DTO.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioPasswordRecuperarDTO(
    @Email @NotBlank String email,
    @NotBlank String codigoRecuperacion,
    @NotBlank String passwordNueva
){}
