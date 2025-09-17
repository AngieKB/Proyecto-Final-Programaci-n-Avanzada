package co.edu.uniquindio.Application.DTO;

public record UsuarioPasswordRecuperarDTO(
    String email,
    String codigoRecuperacion,
    String passwordNueva
){}
