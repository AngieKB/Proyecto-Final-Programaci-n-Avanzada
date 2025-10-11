package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.DTO.ResponseDTO;
import co.edu.uniquindio.Application.DTO.Usuario.ChangePasswordDTO;
import co.edu.uniquindio.Application.DTO.Usuario.EditarUsuarioDTO;
import co.edu.uniquindio.Application.DTO.Usuario.UsuarioDTO;
import co.edu.uniquindio.Application.Model.Rol;
import co.edu.uniquindio.Application.Services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UsuarioControllerPUTest {
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsuarioExitoso() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                1L, "Ana", "123456789", "ana@example.com",
                "http://fake.url/foto.jpg", Rol.HUESPED
        );

        when(usuarioService.get(1L)).thenReturn(usuarioDTO);

        ResponseEntity<ResponseDTO<UsuarioDTO>> response = usuarioController.get(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().error());
        assertEquals("Ana", response.getBody().content().nombre());
        verify(usuarioService).get(1L);
    }

    @Test
    void testDeleteUsuarioExitoso() throws Exception {
        doNothing().when(usuarioService).delete(1L);

        ResponseEntity<ResponseDTO<String>> response = usuarioController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("El usuario ha sido eliminado", response.getBody().content());
        verify(usuarioService).delete(1L);
    }

    @Test
    void testEditUsuarioExitoso() throws Exception {
        MockMultipartFile foto = new MockMultipartFile("foto", "foto.jpg", "image/jpeg", "data".getBytes());

        doNothing().when(usuarioService).edit(eq(1L), any(EditarUsuarioDTO.class));

        ResponseEntity<ResponseDTO<String>> response = usuarioController.edit(
                1L, "NuevoNombre", "123456789", foto
        );

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("El usuario ha sido actualizado", response.getBody().content());
        verify(usuarioService).edit(eq(1L), any(EditarUsuarioDTO.class));
    }

    @Test
    void testListAllUsuariosExitoso() {
        List<UsuarioDTO> lista = List.of(
                new UsuarioDTO(1L, "Ana", "111", "ana@example.com", "url1", Rol.HUESPED),
                new UsuarioDTO(2L, "Carlos", "222", "carlos@example.com", "url2", Rol.ANFITRION)
        );

        when(usuarioService.listAll()).thenReturn(lista);

        ResponseEntity<ResponseDTO<List<UsuarioDTO>>> response = usuarioController.listAll();

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().error());
        assertEquals(2, response.getBody().content().size());
        verify(usuarioService).listAll();
    }

    @Test
    void testChangePasswordExitoso() throws Exception {
        ChangePasswordDTO dto = new ChangePasswordDTO("oldPass123", "newPass456");

        doNothing().when(usuarioService).changePassword(1L, dto);

        ResponseEntity<ResponseDTO<String>> response =
                usuarioController.changePassword(dto, 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Contraseña actualizada exitosamente", response.getBody().content());
        verify(usuarioService).changePassword(1L, dto);
    }
}
