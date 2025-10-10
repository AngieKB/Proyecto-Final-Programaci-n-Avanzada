package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.TokenDTO;
import co.edu.uniquindio.Application.DTO.Usuario.*;

import java.util.List;

public interface UsuarioService {
    void create(CrearUsuarioDTO userDTO) throws Exception;
    UsuarioDTO get(Long id) throws Exception;
    void delete(Long id) throws Exception;
    List<UsuarioDTO> listAll();
    void edit(Long id, EditarUsuarioDTO userDTO) throws Exception;
    void changePassword(Long id, ChangePasswordDTO changePasswordDTO) throws Exception;
    void resetPassword(ResetPasswordDTO resetPassWordDTO) throws Exception;
    TokenDTO login(LoginDTO loginDTO) throws Exception;
    void sendVerificationCode(ForgotPasswordDTO forgotPasswordDTO) throws Exception;
}
