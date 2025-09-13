package co.edu.uniquindio.Model;

import jakarta.persistence.*;
import lombok.*;
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
    private String ciudad;
    private String direccion;
    private Double latitud;
    private Double longitud;
    private Double precioNoche;
    private int capacidadMax;

    @OneToMany(mappedBy = "alojamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "alojamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    @Enumerated(EnumType.STRING)
    private EstadoAlojamiento estado;


}
