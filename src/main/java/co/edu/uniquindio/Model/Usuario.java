package co.edu.uniquindio.Model;

import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nombre;
    private String telefono;
    private String email;
    private String password;
    private String fotoUrl;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaCreacion;
    //private Role rol;
    //private UserStatus estadoUsuario;
}
