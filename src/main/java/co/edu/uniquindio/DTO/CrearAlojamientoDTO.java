package co.edu.uniquindio.DTO;

import co.edu.uniquindio.Model.EstadoAlojamiento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.List;

public record CrearAlojamientoDTO(
        @NotBlank @Length(max = 100) String titulo,
        @NotBlank @Length(max = 300) String descripcion,
        @NotNull List<String> servicios,
        @NotNull List<String> galeria,
        @NotBlank @Length(max = 20) String ciudad,
        @NotBlank @Length(max = 50) String direccion,
        @NotNull @Length(max = 20) Double latitud,
        @NotNull @Length(max = 20) Double longitud,
        @NotNull @Length(max = 30) Double precioNoche,
        @NotNull int capacidadMax
) {
}
