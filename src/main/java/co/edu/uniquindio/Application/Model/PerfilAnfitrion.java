package co.edu.uniquindio.Application.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PerfilAnfitrion {
    @Id @GeneratedValue
    private Long id;

    private String descripcion;

    @ElementCollection
    private List<String> documentosLegales;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "anfitrion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alojamiento> alojamientos = new ArrayList<>();
}
