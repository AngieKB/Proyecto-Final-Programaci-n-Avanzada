package co.edu.uniquindio.Application.DTO;

public record UsuarioPasswordCambiarDTO(
    Long usuarioId,
    String passwordActual,
    String passwordNueva
){}
