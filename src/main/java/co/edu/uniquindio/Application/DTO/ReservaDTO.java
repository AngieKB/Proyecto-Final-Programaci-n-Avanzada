package co.edu.uniquindio.Application.DTO;

import co.edu.uniquindio.Application.Model.*;

import java.time.LocalDateTime;

public record ReservaDTO (
        Long id,
        Long alojamientoId,
        Long usuarioId,
        LocalDateTime fechaCheckIn,
        LocalDateTime fechaCheckOut,
        Integer cantidadHuespedes,
        Double total,
        EstadoReserva estadoReserva
){
}
