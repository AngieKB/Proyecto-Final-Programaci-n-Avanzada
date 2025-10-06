package co.edu.uniquindio.Application.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue
    @Column(length = 20, nullable = false)
    private Long id;

    private String nombre;

    @Column(unique =true, nullable=false)
    private String email;

    private String telefono;
    private String password;
    private String fotoUrl;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "huesped", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_favoritos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "alojamiento_id")
    )
    private List<Alojamiento> favoritos = new ArrayList<>();

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private PerfilAnfitrion perfilAnfitrion;

    //private UserStatus estadoUsuario;
}
