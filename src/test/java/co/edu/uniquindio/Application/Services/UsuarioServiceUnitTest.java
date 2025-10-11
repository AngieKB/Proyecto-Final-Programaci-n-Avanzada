package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.TokenDTO;
import co.edu.uniquindio.Application.DTO.Usuario.*;
import co.edu.uniquindio.Application.Exceptions.*;
import co.edu.uniquindio.Application.Mappers.UsuarioMapper;
import co.edu.uniquindio.Application.Model.Rol;
import co.edu.uniquindio.Application.Model.Usuario;
import co.edu.uniquindio.Application.Repository.UsuarioRepository;
import co.edu.uniquindio.Application.Security.JWTUtils;
import co.edu.uniquindio.Application.Services.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceUnitTest {

    @Mock
    private UsuarioMapper usuarioMapper;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ImageService imageService;
    @Mock
    private JWTUtils jwtUtils;
    @Mock
    private EmailService emailService;

    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
        usuarioService = new UsuarioServiceImpl(usuarioMapper, usuarioRepository, passwordEncoder, imageService, jwtUtils, emailService);
    }

    @Test
    void testCreateUsuarioExitoso() throws Exception {
        MultipartFile mockFoto = mock(MultipartFile.class);
        CrearUsuarioDTO dto = new CrearUsuarioDTO(
                "Ana",
                "ana@example.com",
                "3001234567",
                "Password123",
                LocalDate.of(1990, 5, 15),
                mockFoto
        );

        Usuario usuario = new Usuario();
        usuario.setEmail("ana@example.com");
        usuario.setNombre("Ana");

        when(usuarioRepository.findByEmail(dto.email())).thenReturn(Optional.empty());
        when(usuarioMapper.toEntity(dto)).thenReturn(usuario);
        when(imageService.upload(dto.fotoUrl())).thenReturn(Map.of("url", "http://fake.url/foto.jpg"));

        usuarioService.create(dto);

        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void testCreateUsuarioDuplicado() {
        MultipartFile mockFoto = mock(MultipartFile.class);
        CrearUsuarioDTO dto = new CrearUsuarioDTO(
                "Ana",
                "ana@example.com",
                "3001234567",
                "Password123",
                LocalDate.of(1990, 5, 15),
                mockFoto
        );

        when(usuarioRepository.findByEmail(dto.email())).thenReturn(Optional.of(new Usuario()));

        assertThrows(ValueConflictException.class, () -> usuarioService.create(dto));
    }

    @Test
    void testGetUsuarioExitoso() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Carlos");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(new UsuarioDTO(1L, "Carlos", "123", "email", null, Rol.HUESPED));

        UsuarioDTO result = usuarioService.get(1L);
        assertEquals("Carlos", result.nombre());
    }

    @Test
    void testGetUsuarioNoExiste() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> usuarioService.get(1L));
    }

    @Test
    void testDeleteUsuarioExitoso() throws Exception {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        usuarioService.delete(1L);
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void testDeleteUsuarioNoExiste() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);
        assertThrows(NotFoundException.class, () -> usuarioService.delete(1L));
    }

    @Test
    void testEditUsuarioExitoso() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        EditarUsuarioDTO dto = new EditarUsuarioDTO("Nuevo", "987", null);

        usuarioService.edit(1L, dto);

        verify(usuarioMapper).updateUsuarioFromDto(dto, usuario);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testChangePasswordExitoso() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("user@test.com");
        usuario.setPassword(passwordEncoder.encode("oldPass"));

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        ChangePasswordDTO dto = new ChangePasswordDTO("oldPass", "newPass");

        usuarioService.changePassword(1L, dto);

        verify(usuarioRepository).save(usuario);
        verify(emailService).sendMail(any());
    }

    @Test
    void testChangePasswordIncorrecta() {
        Usuario usuario = new Usuario();
        usuario.setPassword(passwordEncoder.encode("otraPass"));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        ChangePasswordDTO dto = new ChangePasswordDTO("wrong", "new");
        assertThrows(ValidationException.class, () -> usuarioService.changePassword(1L, dto));
    }

    @Test
    void testChangePasswordIgualAnterior() {
        Usuario usuario = new Usuario();
        usuario.setPassword(passwordEncoder.encode("same"));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        ChangePasswordDTO dto = new ChangePasswordDTO("same", "same");
        assertThrows(ValueConflictException.class, () -> usuarioService.changePassword(1L, dto));
    }

    //  RESET PASSWORD
    @Test
    void testResetPasswordExitoso() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setCodigoVerificacion("123456");
        usuario.setCodigoExpiraEn(LocalDateTime.now().plusMinutes(5));

        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));

        ResetPasswordDTO dto = new ResetPasswordDTO("test@example.com", "123456", "newPass");

        usuarioService.resetPassword(dto);

        verify(usuarioRepository).save(usuario);
        verify(emailService).sendMail(any());
        assertNull(usuario.getCodigoVerificacion());
    }

    @Test
    void testResetPasswordCodigoIncorrecto() {
        Usuario usuario = new Usuario();
        usuario.setCodigoVerificacion("111111");
        usuario.setCodigoExpiraEn(LocalDateTime.now().plusMinutes(10));

        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));

        ResetPasswordDTO dto = new ResetPasswordDTO("test@example.com", "999999", "newPass");
        assertThrows(ValidationException.class, () -> usuarioService.resetPassword(dto));
    }

    @Test
    void testResetPasswordCodigoExpirado() {
        Usuario usuario = new Usuario();
        usuario.setCodigoVerificacion("123456");
        usuario.setCodigoExpiraEn(LocalDateTime.now().minusMinutes(1));

        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));

        ResetPasswordDTO dto = new ResetPasswordDTO("test@example.com", "123456", "newPass");
        assertThrows(ValidationException.class, () -> usuarioService.resetPassword(dto));
    }

    @Test
    void testLoginExitoso() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("test@example.com");
        usuario.setPassword(passwordEncoder.encode("1234"));
        usuario.setRol(Rol.HUESPED);

        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));
        when(jwtUtils.generateToken(any(), any())).thenReturn("fake.token");

        TokenDTO token = usuarioService.login(new LoginDTO("test@example.com", "1234"));
        assertEquals("fake.token", token.token());
    }

    @Test
    void testLoginUsuarioNoExiste() {
        when(usuarioRepository.findByEmail("no@correo.com")).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> usuarioService.login(new LoginDTO("no@correo.com", "123")));
    }

    @Test
    void testLoginContrasenaIncorrecta() {
        Usuario usuario = new Usuario();
        usuario.setPassword(passwordEncoder.encode("otra"));
        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));

        assertThrows(Exception.class, () -> usuarioService.login(new LoginDTO("test@example.com", "wrong")));
    }

    @Test
    void testSendVerificationCodeExitoso() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("correo@example.com");

        when(usuarioRepository.findByEmail("correo@example.com")).thenReturn(Optional.of(usuario));

        usuarioService.sendVerificationCode(new ForgotPasswordDTO("correo@example.com"));

        verify(usuarioRepository).save(usuario);
        verify(emailService).sendMail(any());
        assertNotNull(usuario.getCodigoVerificacion());
    }

    @Test
    void testSendVerificationCodeUsuarioNoExiste() {
        when(usuarioRepository.findByEmail("no@existe.com")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> usuarioService.sendVerificationCode(new ForgotPasswordDTO("no@existe.com")));
    }
}
