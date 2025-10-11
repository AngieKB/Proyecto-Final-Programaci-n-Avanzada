package co.edu.uniquindio.Application.Controllers.PruebasUnitarias;

import co.edu.uniquindio.Application.Controllers.PerfilAnfitrionController;
import co.edu.uniquindio.Application.DTO.Anfitrion.EditarAnfitrionDTO;
import co.edu.uniquindio.Application.DTO.Anfitrion.PerfilAnfitrionDTO;
import co.edu.uniquindio.Application.DTO.ResponseDTO;
import co.edu.uniquindio.Application.Services.PerfilAnfitrionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PerfilAnfitrionControllerUnitTest {

    @Mock
    private PerfilAnfitrionService perfilAnfitrionService;

    @InjectMocks
    private PerfilAnfitrionController perfilAnfitrionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerAnfitrionPorId() {
        PerfilAnfitrionDTO dto = new PerfilAnfitrionDTO(1L, 2L, "desc", List.of(), List.of());
        when(perfilAnfitrionService.obtenerPerfil(1L)).thenReturn(dto);

        ResponseEntity<ResponseDTO<PerfilAnfitrionDTO>> response = perfilAnfitrionController.obtenerPerfil(1L);

        assertFalse(response.getBody().error());
        assertEquals(1L, response.getBody().content().id());
        verify(perfilAnfitrionService).obtenerPerfil(1L);
    }

    @Test
    void testListarAnfitriones() {
        var lista = List.of(
                new PerfilAnfitrionDTO(1L, 2L, "desc1", List.of(), List.of()),
                new PerfilAnfitrionDTO(2L, 3L, "desc2", List.of(), List.of())
        );
        when(perfilAnfitrionService.listarPerfiles()).thenReturn(lista);

        var response = perfilAnfitrionController.listarPerfiles();

        assertFalse(response.getBody().error());
        assertEquals(2, response.getBody().content().size());
        verify(perfilAnfitrionService).listarPerfiles();
    }

    @Test
    void testActualizarAnfitrion() {
        EditarAnfitrionDTO dto = new EditarAnfitrionDTO("Ana", "123", "url", "desc");

        ResponseEntity<ResponseDTO<String>> response =
                perfilAnfitrionController.actualizarPerfil(1L, dto);

        assertEquals("Perfil actualizado exitosamente", response.getBody().content());
        verify(perfilAnfitrionService).actualizarPerfil(1L, dto);
    }

    @Test
    void testEliminarAnfitrion() {
        ResponseEntity<ResponseDTO<String>> response =
                perfilAnfitrionController.eliminarPerfil(1L);

        assertEquals("Perfil eliminado exitosamente", response.getBody().content());
        verify(perfilAnfitrionService).eliminarPerfil(1L);
    }
}
