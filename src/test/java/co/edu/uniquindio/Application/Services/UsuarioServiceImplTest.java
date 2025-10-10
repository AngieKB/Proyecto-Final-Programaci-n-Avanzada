package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.Usuario.CrearUsuarioDTO;
import co.edu.uniquindio.Application.Exceptions.ValueConflictException;
import co.edu.uniquindio.Application.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UsuarioServiceImplTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private  UsuarioRepository usuarioRepository;

    @Test
    @Sql("classpath:dataset.sql")
    void testCrearUsuarioExitoso() {
        var nuevo = new CrearUsuarioDTO(
                "Carlos",
                "carlos@email.com",
                "123456789",
                "Password123",
                LocalDate.of(1990, 1, 1)
        );

        assertDoesNotThrow(() -> usuarioService.create(nuevo));
    }

    @Test
    @Sql("classpath:dataset.sql")
    void testCrearUsuario_EmailExistente() throws Exception {
        var existente = usuarioRepository.findById(1L).orElseThrow();
        var duplicado = new CrearUsuarioDTO(
                "Pepe",
                existente.getEmail(),
                "987654321",
                "Pass321",
                LocalDate.of(1995, 5, 5)
        );

        assertThrows(ValueConflictException.class, () -> usuarioService.create(duplicado));
    }

    @Test
    @Sql("classpath:dataset.sql")
    void testObtenerUsuarioPorIdExitoso() throws Exception {
        var usuarioGuardado = usuarioRepository.findById(1L).orElseThrow();
        var usuarioEncontrado = usuarioService.get(usuarioGuardado.getId());

        assertEquals(usuarioGuardado.getNombre(), usuarioEncontrado.nombre());
        assertEquals(usuarioGuardado.getEmail(), usuarioEncontrado.email());
    }

    @Test
    @Sql("classpath:dataset.sql")
    void testObtenerUsuarioPorIdNoExitoso() {
        assertThrows(Exception.class, () -> usuarioService.get(999L));
    }
}
