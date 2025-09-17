package co.edu.uniquindio.Application.DTO;

import co.edu.uniquindio.Application.Model.Alojamiento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RealizarReservaDTO(
        @NotNull @Future LocalDateTime fechaCheckIn,
        @NotNull @Future LocalDateTime fechaCheckOut,
        @NotNull Integer cantidadHuespedes,
        @NotNull UsuarioDTO huesped,
        @NotNull Alojamiento alojamiento
        ) {
}