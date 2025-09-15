package co.edu.uniquindio.Application.DTO;

import java.time.LocalDate;

public record AnfitrionDTO (
        String id,
        String nombre,
        String descripcion,
        String telefono,
        String email,
        String fotoUrl
        //Role rol
){
}