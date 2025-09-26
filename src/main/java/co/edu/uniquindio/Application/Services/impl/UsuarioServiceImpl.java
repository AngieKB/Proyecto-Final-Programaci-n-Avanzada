package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.CrearUsuarioDTO;
import co.edu.uniquindio.Application.DTO.EditarUsuarioDTO;
import co.edu.uniquindio.Application.DTO.UsuarioDTO;
import co.edu.uniquindio.Application.Services.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Override
    public void create(CrearUsuarioDTO userDTO) throws Exception {

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
