package co.edu.uniquindio.Application.DTO.Usuario;

import co.edu.uniquindio.Application.Model.Rol;

public record UsuarioDTO (
        String id,
        String nombre,
        String telefono,
        String email,
        String fotoUrl,
        Rol rol
){}
