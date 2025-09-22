package co.edu.uniquindio.Application.Model;

import jakarta.persistence.*;
import lombok.*;

import javax.xml.stream.Location;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alojamientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alojamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descripcion;

    @ElementCollection
    private List<String> servicios;

    @ElementCollection
    @Column(nullable = false)
    private List<String> galeria;

    @Embedded
    private Ubicacion ubicacion;

    @Column(nullable = false)
    private Double precioNoche;
    @Column(nullable = false)
    private Integer capacidadMax;

    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "alojamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "alojamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAlojamiento estado;

    @ManyToOne
    @JoinColumn(name = "anfitrion_id")
    private PerfilAnfitrion anfitrion;
}
