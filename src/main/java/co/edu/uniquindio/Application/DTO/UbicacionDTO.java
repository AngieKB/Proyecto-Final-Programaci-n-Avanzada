package co.edu.uniquindio.Application.DTO;

import co.edu.uniquindio.Application.Model.Rol;

public record UbicacionDTO(
        String direccion,
        String ciudad,
        String pais,
        Double latitud,
        Double longitud
){}
