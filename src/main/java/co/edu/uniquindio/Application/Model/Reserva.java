package co.edu.uniquindio.Application.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaCheckIn;

    private LocalDateTime fechaCheckOut;

    private Integer cantidadHuespedes;

    private Double total;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    @ManyToOne
    @JoinColumn(name = "huesped_id")
    private Huesped huesped;

    @ManyToOne
    @JoinColumn(name= "alojamiento_id")
    private Alojamiento alojamiento;
}

