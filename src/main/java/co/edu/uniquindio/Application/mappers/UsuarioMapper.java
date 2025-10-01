package co.edu.uniquindio.Application.mappers;

import co.edu.uniquindio.Application.DTO.CrearUsuarioDTO;
import co.edu.uniquindio.Application.DTO.UsuarioDTO;
import co.edu.uniquindio.Application.Model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    UsuarioDTO toDTO(Usuario usuario);
    Usuario toEntity(CrearUsuarioDTO usuarioDTO);
}
