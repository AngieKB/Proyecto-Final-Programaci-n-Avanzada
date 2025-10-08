package co.edu.uniquindio.Application.Mappers;

import co.edu.uniquindio.Application.DTO.Alojamiento.UbicacionDTO;
import co.edu.uniquindio.Application.Model.Ubicacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UbicacionMapper {
    Ubicacion toEntity(UbicacionDTO dto);
    UbicacionDTO toDTO(Ubicacion entity);
}

