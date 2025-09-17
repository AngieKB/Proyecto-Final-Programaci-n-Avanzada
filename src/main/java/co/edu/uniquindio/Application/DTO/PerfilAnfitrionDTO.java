package co.edu.uniquindio.Application.DTO;

import java.util.List;

public record PerfilAnfitrionDTO(
        Long id,
        Long usuarioId,
        String descripcion,
        List<String> domentosLegales,
        List<AlojamientoDTO> alojamientos
){
}