package co.edu.uniquindio.Application.mappers;

import co.edu.uniquindio.Application.DTO.AlojamientoDTO;
import co.edu.uniquindio.Application.DTO.PerfilAnfitrionDTO;
import co.edu.uniquindio.Application.Model.PerfilAnfitrion;

import java.util.stream.Collectors;

public interface PerfilAnfitrionMapper {
    public static PerfilAnfitrionDTO toDTO(PerfilAnfitrion entity) {
        return new PerfilAnfitrionDTO(
                entity.getId(),
                entity.getUsuario() != null ? entity.getUsuario().getId() : null,
                entity.getDescripcion(),
                entity.getDocumentosLegales(),
                entity.getAlojamientos()
                        .stream()
                        .map(aloj -> new AlojamientoDTO(
                                aloj.getId(),
                                aloj.getTitulo(),
                                aloj.getDescripcion(),
                                aloj.getPrecio()
                        ))
                        .collect(Collectors.toList())
        );
    }

    public static PerfilAnfitrion toEntity(PerfilAnfitrionDTO dto) {
        PerfilAnfitrion entity = new PerfilAnfitrion();
        entity.setId(dto.id());
        entity.setDescripcion(dto.descripcion());
        entity.setDocumentosLegales(dto.domentosLegales());
        return entity;
    }
}
