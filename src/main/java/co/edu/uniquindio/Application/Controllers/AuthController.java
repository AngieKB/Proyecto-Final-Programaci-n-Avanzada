package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.DTO.Anfitrion.CrearAnfitrionDTO;
import co.edu.uniquindio.Application.DTO.TokenDTO;
import co.edu.uniquindio.Application.DTO.Usuario.*;
import co.edu.uniquindio.Application.DTO.ResponseDTO;
import co.edu.uniquindio.Application.Services.PerfilAnfitrionService;
import co.edu.uniquindio.Application.Services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService userService;
    private final PerfilAnfitrionService perfilAnfitrionService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception{
        TokenDTO token = userService.login(loginDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, token));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<String>> create(@Valid @ModelAttribute CrearUsuarioDTO userDTO) throws Exception{
        System.out.println("Nombre: " + userDTO.nombre());
        System.out.println("Email: " + userDTO.email());
        System.out.println("Telefono: " + userDTO.telefono());
        userService.create(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El registro ha sido exitoso"));
    }

    @PostMapping("/host-register")
    public ResponseEntity<ResponseDTO<String>> crearPerfilAnfitrion(@Valid @RequestBody CrearAnfitrionDTO dto) {
        perfilAnfitrionService.crearPerfil(dto);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Perfil creado exitosamente"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDTO<String>> sendVerificationCode(@RequestBody ForgotPasswordDTO forgotPasswordDTO) throws Exception{
        //TODO llamar al servicio para enviar el código
        userService.sendVerificationCode(forgotPasswordDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Código enviado"));
    }

    @PutMapping("/reset-password")
    public ResponseEntity<ResponseDTO<String>> changePassword(@RequestBody ResetPasswordDTO resetPasswordDTO) throws Exception{
        userService.resetPassword(resetPasswordDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Contraseña cambiada"));
    }
}