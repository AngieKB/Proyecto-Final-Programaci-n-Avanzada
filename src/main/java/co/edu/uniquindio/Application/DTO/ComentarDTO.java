package co.edu.uniquindio.Application.DTO;

import co.edu.uniquindio.Application.Model.Alojamiento;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record ComentarDTO(
        @NotBlank @Length(max = 200) String comentario,
        @NotNull @Max(5) @Min(1) int calificacion,
        @NotNull LocalDateTime fecha,
        @NotNull UsuarioDTO autor,
        @NotNull Alojamiento alojamiento
) { }
