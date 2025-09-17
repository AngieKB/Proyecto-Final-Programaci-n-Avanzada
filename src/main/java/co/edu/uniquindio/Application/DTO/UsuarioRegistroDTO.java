package co.edu.uniquindio.Application.DTO;

import co.edu.uniquindio.Application.Model.Rol;

import java.time.LocalDate;

public record UsuarioRegistroDTO(
    String nombre,
    String email,
    String telefono,
    String password,
    Rol rol,
    LocalDate fechaNacimiento
){}
