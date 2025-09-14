package co.edu.uniquindio.DTO;

import co.edu.uniquindio.Model.Comentario;
import co.edu.uniquindio.Model.EstadoAlojamiento;
import co.edu.uniquindio.Model.Reserva;

import java.util.List;

public record AlojamientoDTO (
    Long id,
    String titulo,
    String descripcion,
    List<String> servicios,
    List<String> galeria,
    String ciudad,
    String direccion,
    Double latitud,
    Double longitud,
    Double precioNoche,
    int capacidadMax,
    List<Comentario> comentarios,
    List<Reserva> reservas,
    EstadoAlojamiento estado
){
}
