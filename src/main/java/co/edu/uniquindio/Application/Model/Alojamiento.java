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
    private Long id;

    private String titulo;
    private String descripcion;

    @ElementCollection
    private List<String> servicios;

    @ElementCollection
    private List<String> galeria;

    @Embedded
    private Ubicacion ubicacion;

    private Double precioNoche;
    private Integer capacidadMax;

    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "alojamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "alojamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private EstadoAlojamiento estado;

    @ManyToOne
    @JoinColumn(name = "anfitrion_id")
    private PerfilAnfitrion anfitrion;
}
