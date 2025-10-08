package co.edu.uniquindio.Application.Mappers;

import co.edu.uniquindio.Application.DTO.Usuario.CrearUsuarioDTO;
import co.edu.uniquindio.Application.DTO.Usuario.EditarUsuarioDTO;
import co.edu.uniquindio.Application.DTO.Usuario.UsuarioDTO;
import co.edu.uniquindio.Application.Model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    UsuarioDTO toDTO(Usuario usuario);
    Usuario toEntity(CrearUsuarioDTO usuarioDTO);
    void updateUserFromDto(EditarUsuarioDTO dto, @MappingTarget Usuario usuario);
}
