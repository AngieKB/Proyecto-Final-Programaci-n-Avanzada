package co.edu.uniquindio.Application.DTO;

import co.edu.uniquindio.Application.Model.Alojamiento;
import co.edu.uniquindio.Application.Model.EstadoReserva;
import co.edu.uniquindio.Application.Model.Huesped;

import java.time.LocalDateTime;

public record ReservaDTO (
        Long id,
        LocalDateTime fechaCheckIn,
        LocalDateTime fechaCheckOut,
        Integer cantidadHuespedes,
        Double total,
        EstadoReserva estadoReserva,
        Huesped huesped,
        Alojamiento alojamiento
){
}
