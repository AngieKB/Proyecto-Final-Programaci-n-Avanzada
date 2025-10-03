package co.edu.uniquindio.Application.DTO.Comentario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ResponderDTO(
        @NotBlank @Length(max = 300) String respuesta,
        @NotNull Long idComentario,
        @NotNull Long idAnfitrion
) {}