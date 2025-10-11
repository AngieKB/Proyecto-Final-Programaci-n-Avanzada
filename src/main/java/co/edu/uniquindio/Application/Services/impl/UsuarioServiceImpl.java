package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.EmailDTO;
import co.edu.uniquindio.Application.DTO.TokenDTO;
import co.edu.uniquindio.Application.DTO.Usuario.*;
import co.edu.uniquindio.Application.Exceptions.NotFoundException;
import co.edu.uniquindio.Application.Exceptions.ValidationException;
import co.edu.uniquindio.Application.Exceptions.ValueConflictException;
import co.edu.uniquindio.Application.Model.Usuario;
import co.edu.uniquindio.Application.Repository.UsuarioRepository;
import co.edu.uniquindio.Application.Security.JWTUtils;
import co.edu.uniquindio.Application.Services.EmailService;
import co.edu.uniquindio.Application.Services.ImageService;
import co.edu.uniquindio.Application.Services.UsuarioService;
import co.edu.uniquindio.Application.Mappers.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;
    private final JWTUtils jwtUtils;
    private final EmailService emailService;

    @Override
    public void create(CrearUsuarioDTO usuarioDTO) throws Exception {
        if(isEmailDuplicated(usuarioDTO.email())){
            throw new ValueConflictException("El correo electrónico ya está en uso.");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario newUsuario = usuarioMapper.toEntity(usuarioDTO);
        System.out.println("Entidad mapeada: " + newUsuario.getEmail()+", "+newUsuario.getNombre());
        newUsuario.setPassword(passwordEncoder.encode(usuarioDTO.password()));
        Map foto = imageService.upload(usuarioDTO.fotoUrl());
        String fotoUrl = foto.get("url").toString();
        newUsuario.setFotoUrl(fotoUrl);
        usuarioRepository.save(newUsuario);
    }

    private boolean isEmailDuplicated(String email){
        return usuarioRepository.findByEmail(email).isPresent();
    }

    @Override
    public UsuarioDTO get(Long id) throws Exception {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("El usuario con id " + id + " no existe"));
    }

    @Override
    public void delete (Long id) throws Exception {
        if (!usuarioRepository.existsById(id)) {
            throw new NotFoundException("El usuario con id " + id + " no existe");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<UsuarioDTO> listAll() {
        return List.of();
    }

    @Override
    public void edit(Long id, EditarUsuarioDTO userDTO) throws Exception {
        Usuario usuario = getUsuarioById(id);
        usuarioMapper.updateUsuarioFromDto(userDTO, usuario);
        usuarioRepository.save(usuario);
    }

    @Override
    public void changePassword(Long id, ChangePasswordDTO changePasswordDTO) throws Exception {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario user = getUsuarioById(id);

        if(!passwordEncoder.matches(changePasswordDTO.oldPassword(), user.getPassword())){
            throw new ValidationException("La contraseña actual es incorrecta.");
        }

        if(changePasswordDTO.oldPassword().equals(changePasswordDTO.newPassword())){
            throw new ValueConflictException("La nueva contraseña no puede ser igual a la actual.");
        }

        user.setPassword( passwordEncoder.encode(changePasswordDTO.newPassword()) );

        usuarioRepository.save(user);

        emailService.sendMail(
                new EmailDTO("Tu contraseña se ha actualizado correctamente",
                        "Usted realizó un cambio de contraseña", user.getEmail())
        );
    }

    @Transactional
    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(resetPasswordDTO.email()).orElseThrow(() -> new NotFoundException("El usuario no existe"));

        // Se verifica que el código coincide
        if (usuario.getCodigoVerificacion() == null ||
                !usuario.getCodigoVerificacion().equals(resetPasswordDTO.verificationCode())) {
            throw new ValidationException("El código de verificación es incorrecto");
        }

        // Se verifica que el código no haya expirado
        if (usuario.getCodigoExpiraEn() == null || usuario.getCodigoExpiraEn().isBefore(LocalDateTime.now())) {
            usuario.setCodigoVerificacion(null);
            usuario.setCodigoExpiraEn(null);
            throw new ValidationException("El código de verificación ha expirado");
        }

        // Actualizamos la contraseña
        usuario.setPassword(passwordEncoder.encode(resetPasswordDTO.newPassword()));

        //Se limpia el código usado
        usuario.setCodigoVerificacion(null);
        usuario.setCodigoExpiraEn(null);

        usuarioRepository.save(usuario);

        emailService.sendMail(
                new EmailDTO("Tu contraseña se ha actualizado correctamente",
                        "Usted realizó un cambio de contraseña",
                        usuario.getEmail())
        );
    }

    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        Optional<Usuario> optionalUser = usuarioRepository.findByEmail(loginDTO.email());

        if(optionalUser.isEmpty()){
            throw new Exception("El usuario no existe");
        }

        Usuario usuario = optionalUser.get();

        // Verificar si la contraseña es correcta usando el PasswordEncoder
        if(!passwordEncoder.matches(loginDTO.password(), usuario.getPassword())){
            throw new Exception("El usuario no existe");
        }

        String token = jwtUtils.generateToken(usuario.getId().toString(), createClaims(usuario));
        return new TokenDTO(token);
    }

    @Override
    public void sendVerificationCode(ForgotPasswordDTO forgotPasswordDTO) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(forgotPasswordDTO.email())
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));

        // Generar un código aleatorio de 6 dígitos
        String codigo = String.valueOf((int)(Math.random() * 900000) + 100000);

        // Guardar el código y su fecha de expiración
        usuario.setCodigoVerificacion(codigo);
        usuario.setCodigoExpiraEn(LocalDateTime.now().plusMinutes(10));
        usuarioRepository.save(usuario);

        // Enviar el correo
        emailService.sendMail(
                new EmailDTO(
                        "Recuperación de contraseña",
                        "Su código de verificación es: " + codigo + "\nEste código expira en 10 minutos.",
                        usuario.getEmail()
                )
        );
    }


    private Map<String, String> createClaims(Usuario usuario) {
        String email = usuario.getEmail() != null ? usuario.getEmail() : "";
        String nombre = usuario.getNombre() != null ? usuario.getNombre() : "";
        String rol = (usuario.getRol() != null) ? "ROLE_" + usuario.getRol().name() : "ROLE_USER";

        return Map.of(
                "email", email,
                "name", nombre,
                "role", rol
        );
    }

    private Usuario getUsuarioById(Long id) throws Exception{
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado."));
    }
}