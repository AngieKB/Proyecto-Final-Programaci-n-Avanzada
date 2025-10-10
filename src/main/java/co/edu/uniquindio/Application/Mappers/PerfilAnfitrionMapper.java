package co.edu.uniquindio.Application.Mappers;

import co.edu.uniquindio.Application.DTO.Anfitrion.CrearAnfitrionDTO;
import co.edu.uniquindio.Application.DTO.Anfitrion.EditarAnfitrionDTO;
import co.edu.uniquindio.Application.DTO.Anfitrion.PerfilAnfitrionDTO;

import co.edu.uniquindio.Application.Model.PerfilAnfitrion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PerfilAnfitrionMapper {
    PerfilAnfitrionDTO toDTO(PerfilAnfitrion entity);
    //@Mapping(target = "rol", constant = "HUESPED")
    PerfilAnfitrion toEntity(CrearAnfitrionDTO dto);
    void updatePerfilAnfitrionFromDto(EditarAnfitrionDTO dto, @MappingTarget PerfilAnfitrion anfitrion);
}