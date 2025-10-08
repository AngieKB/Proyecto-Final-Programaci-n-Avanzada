package co.edu.uniquindio.Application.DTO.Anfitrion;

import co.edu.uniquindio.Application.Model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record CrearAnfitrionDTO(
        @NotBlank @Length(max = 100) String nombre,
        @Length(max = 10) String telefono,
        @NotBlank @Length(max = 50) @Email(message = "Escriba un email válido.") String email,
        @NotBlank @Length(min = 7, max = 20) String password,
        @Length(max = 300) String fotoUrl,
        @NotNull @Past LocalDate fechaNacimiento,
        @NotBlank @Length(max = 300) String descripcion,
        @NotNull List<String> documentosLegales,
        @NotBlank String usuarioId
) {
}