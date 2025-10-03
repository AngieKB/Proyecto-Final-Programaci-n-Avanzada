package co.edu.uniquindio.Application.Mappers;

import co.edu.uniquindio.Application.DTO.Comentario.ResponderDTO;
import co.edu.uniquindio.Application.DTO.Comentario.RespuestaDTO;
import co.edu.uniquindio.Application.Model.Respuesta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RespuestaMapper {
    Respuesta toEntity(ResponderDTO dto);
    RespuestaDTO toDTO(Respuesta entity);
}
