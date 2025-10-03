package co.edu.uniquindio.Application.Mappers;

import co.edu.uniquindio.Application.DTO.Comentario.ComentarDTO;
import co.edu.uniquindio.Application.DTO.Comentario.ComentarioDTO;
import co.edu.uniquindio.Application.Model.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ComentarioMapper {
    Comentario toEntity(ComentarDTO dto);
    ComentarioDTO toDto(Comentario entity);
}
