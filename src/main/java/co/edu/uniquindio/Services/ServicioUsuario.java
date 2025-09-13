package co.edu.uniquindio.Services;

public interface ServicioUsuario {
    void create(CreateUserDTO userDTO) throws Exception;

    UsuarioDTO get(String id) throws Exception;

    void delete(String id) throws Exception;

    List<UserDTO> listAll();

    void edit(String id, EditUserDTO userDTO) throws Exception;
}
