package co.edu.uniquindio.Application.DTO.Usuario;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record EditarUsuarioDTO(
        @NotBlank @Size(max = 50) String nombre,
        @NotBlank @Size(max = 10) String telefono,
        @NotBlank @URL String fotoUrl
){}
