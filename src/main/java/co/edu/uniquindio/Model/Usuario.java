package co.edu.uniquindio.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public abstract class Usuario {
    private String id;
    private String nombre;
    private String telefono;
    private String email;
    private String password;
    private String fotoUrl;
    private LocalDate fechaNacimiento;
    private LocalDateTime creadoEl;
    //private Role rol;
    //private UserStatus estadoUsuario;
}
