package co.edu.uniquindio.Application.DTO.Usuario;

import co.edu.uniquindio.Application.Model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CrearUsuarioDTO(
    @NotBlank @Length(max=30) String nombre,
    @Email @NotBlank String email,
    @NotBlank @Length(max=10) String telefono,
    @NotBlank String password,
    @NotNull Rol rol,
    @NotNull @Past LocalDate fechaNacimiento
){}
