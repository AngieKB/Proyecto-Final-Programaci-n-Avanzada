package co.edu.uniquindio.Application.DTO.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordDTO(
        @NotBlank @Email String email
) {
}
