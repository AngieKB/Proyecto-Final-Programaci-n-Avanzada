package co.edu.uniquindio.Application.DTO.Usuario;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordDTO(
        @NotBlank String oldPassword,
        @NotBlank String newPassword
) {
}
