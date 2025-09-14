package co.edu.uniquindio.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EditarAnfitrionDTO(
        @NotBlank @Length(max = 100) String nombre,
        @Length(max = 10) String telefono,
        @Length(max = 300) String fotoUrl,
        @Length(max = 300) String descripcion
) {
}
