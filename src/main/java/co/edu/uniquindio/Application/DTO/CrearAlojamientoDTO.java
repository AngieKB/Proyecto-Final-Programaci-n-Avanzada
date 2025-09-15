package co.edu.uniquindio.Application.DTO;

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
        @NotNull Double latitud,
        @NotNull Double longitud,
        @NotNull Double precioNoche,
        @NotNull Integer capacidadMax
) {}