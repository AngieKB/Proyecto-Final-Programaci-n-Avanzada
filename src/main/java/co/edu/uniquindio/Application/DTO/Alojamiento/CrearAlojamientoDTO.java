package co.edu.uniquindio.Application.DTO.Alojamiento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public record CrearAlojamientoDTO(
        @NotBlank @Length(max = 100) String titulo,
        @NotBlank @Length(max = 300) String descripcion,
        @NotNull List<String> servicios,
        @NotNull List<MultipartFile> galeria,
        @NotBlank @Length(max = 20) String ciudad,
        @NotBlank @Length(max = 50) String direccion,
        @NotNull Double latitud,
        @NotNull Double longitud,
        @NotNull Double precioNoche,
        @NotNull Integer capacidadMax
) {}