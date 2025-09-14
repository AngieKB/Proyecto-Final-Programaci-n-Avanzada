package co.edu.uniquindio.DTO;

import co.edu.uniquindio.Model.Alojamiento;
import co.edu.uniquindio.Model.EstadoReserva;
import co.edu.uniquindio.Model.Huesped;

import java.time.LocalDateTime;

public record ReservaDTO (
        Long id,
        LocalDateTime fechaCheckIn,
        LocalDateTime fechaCheckOut,
        Integer cantidadHuespedes,
        float total,
        EstadoReserva estadoReserva,
        Huesped huesped,
        Alojamiento alojamiento
){
}
