package co.edu.uniquindio.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import java.util.Date;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID autoincremental, clave primaria

    private LocalDateTime fechaCheckIn;

    private LocalDateTime fechaCheckOut;

    private Integer cantidadHuespedes;

    private Double total;

    private EstadoReserva estado;

    private Huesped huesped;

    private Alojamiento alojamiento;
}

