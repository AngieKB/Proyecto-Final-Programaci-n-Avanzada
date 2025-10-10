package co.edu.uniquindio.Application.Mappers;

import co.edu.uniquindio.Application.DTO.Usuario.CrearUsuarioDTO;
import co.edu.uniquindio.Application.DTO.Usuario.EditarUsuarioDTO;
import co.edu.uniquindio.Application.DTO.Usuario.UsuarioDTO;
import co.edu.uniquindio.Application.Model.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
    @Mapping(target = "rol", constant = "HUESPED")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    Usuario toEntity(CrearUsuarioDTO usuarioDTO);
    UsuarioDTO toDTO(Usuario usuario);
    void updateUsuarioFromDto(EditarUsuarioDTO dto, @MappingTarget Usuario usuario);
}
