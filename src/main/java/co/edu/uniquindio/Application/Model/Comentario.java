package co.edu.uniquindio.Application.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20, nullable = false)
    private Long id;

    private String texto;

    private Integer calificacion; //de 1 a 5

    private LocalDateTime fecha;

    @OneToOne(mappedBy = "comentario", cascade = CascadeType.ALL)
    private Respuesta respuesta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario huesped;

    @ManyToOne
    @JoinColumn(name= "alojamiento_id")
    private Alojamiento alojamiento;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

}

