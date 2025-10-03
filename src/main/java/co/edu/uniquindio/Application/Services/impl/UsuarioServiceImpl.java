package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.Usuario.CrearUsuarioDTO;
import co.edu.uniquindio.Application.DTO.Usuario.EditarUsuarioDTO;
import co.edu.uniquindio.Application.DTO.Usuario.UsuarioDTO;
import co.edu.uniquindio.Application.Model.Usuario;
import co.edu.uniquindio.Application.Services.UsuarioService;
import co.edu.uniquindio.Application.Mappers.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioMapper usuarioMapper;

    @Override
    public void create(CrearUsuarioDTO usuarioDTO) throws Exception {
        Usuario newUsuario = usuarioMapper.toEntity(usuarioDTO);

    }

    @Override
    public UsuarioDTO get(String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public List<UsuarioDTO> listAll() {
        return List.of();
    }

    @Override
    public void edit(String id, EditarUsuarioDTO userDTO) throws Exception {

    }
}
