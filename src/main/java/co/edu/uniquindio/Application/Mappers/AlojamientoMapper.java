package co.edu.uniquindio.Application.Mappers;

import co.edu.uniquindio.Application.DTO.Alojamiento.AlojamientoDTO;
import co.edu.uniquindio.Application.DTO.Alojamiento.CrearAlojamientoDTO;
import co.edu.uniquindio.Application.Model.Alojamiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlojamientoMapper {
    @Mapping(target = "galeria", ignore = true)
    Alojamiento toEntity(CrearAlojamientoDTO dto);
    AlojamientoDTO toDTO(Alojamiento entity);
}
