package co.edu.uniquindio.Application.mappers;

import co.edu.uniquindio.Application.DTO.ComentarioDTO;
import co.edu.uniquindio.Application.Model.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ComentarioMapper {
    Comentario toEntity(ComentarioDTO dto);
    ComentarioDTO toDto(Comentario entity);
}
