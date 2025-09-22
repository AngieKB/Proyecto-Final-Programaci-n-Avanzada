package co.edu.uniquindio.Application.mappers;

import co.edu.uniquindio.Application.DTO.UbicacionDTO;
import co.edu.uniquindio.Application.Model.Ubicacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UbicacionMapper {
    Ubicacion toEntity(UbicacionDTO dto);
    UbicacionDTO toDTO(Ubicacion entity);
}

