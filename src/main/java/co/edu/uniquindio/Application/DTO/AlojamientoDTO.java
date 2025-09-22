package co.edu.uniquindio.Application.DTO;

import co.edu.uniquindio.Application.Model.Comentario;
import co.edu.uniquindio.Application.Model.EstadoAlojamiento;
import co.edu.uniquindio.Application.Model.Reserva;
import lombok.*;

import java.util.List;

public record AlojamientoDTO (
    Long id,
    String titulo,
    String descripcion,
    List<String> servicios,
    List<String> galeria,
    UbicacionDTO ubicacion,
    Double precioNoche,
    Integer capacidadMax,
    List<Comentario> comentarios,
    List<Reserva> reservas,
    EstadoAlojamiento estado
){
}
