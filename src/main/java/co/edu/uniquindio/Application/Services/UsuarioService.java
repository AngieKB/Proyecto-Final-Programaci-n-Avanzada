package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.*;

import java.util.List;

public interface UsuarioService {
    void create(CrearUsuarioDTO userDTO) throws Exception;

    UsuarioDTO get(String id) throws Exception;

    void delete(String id) throws Exception;

    List<UsuarioDTO> listAll();

    void edit(String id, EditarUsuarioDTO userDTO) throws Exception;
}
