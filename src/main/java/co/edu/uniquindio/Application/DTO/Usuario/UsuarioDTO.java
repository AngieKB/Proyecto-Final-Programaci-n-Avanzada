package co.edu.uniquindio.Application.DTO.Usuario;

import co.edu.uniquindio.Application.Model.Rol;

public record UsuarioDTO (
        Long id,
        String nombre,
        String telefono,
        String email,
        String fotoUrl,
        Rol rol
){}
