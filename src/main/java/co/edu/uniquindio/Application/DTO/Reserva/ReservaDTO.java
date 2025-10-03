package co.edu.uniquindio.Application.DTO.Reserva;

import co.edu.uniquindio.Application.Model.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record ReservaDTO (
        Long id,
        @NotNull Long alojamientoId,
        @NotNull Long usuarioId,
        @NotNull LocalDateTime fechaCheckIn,
        @NotNull LocalDateTime fechaCheckOut,
        @NotNull @Min(1) Integer cantidadHuespedes,
        @NotNull Double total,
        @NotNull @PositiveOrZero EstadoReserva estadoReserva
){
}
