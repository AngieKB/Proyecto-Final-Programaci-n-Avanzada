package co.edu.uniquindio.Application.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

public record UsuarioActualizacionDTO(
        @NotBlank @Length(max = 50) String nombre,
        @NotBlank @Length(max = 10) String telefono,
        @NotBlank @URL String fotoUrl
){}
