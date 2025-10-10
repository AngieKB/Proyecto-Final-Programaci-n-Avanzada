package co.edu.uniquindio.Application.DTO.Usuario;

import co.edu.uniquindio.Application.Model.Rol;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record CrearUsuarioDTO(
    @NotBlank @Size(max=30) String nombre,
    @Email @NotBlank String email,
    @NotBlank @Size(max=10) String telefono,
    @NotBlank @Size(min=8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).*$",
            message = "La contraseña debe contener al menos una letra mayúscula, una letra minúsucla, y un número"
    )
            String password,
    @NotNull @Past LocalDate fechaNacimiento,
    MultipartFile fotoUrl
){}
