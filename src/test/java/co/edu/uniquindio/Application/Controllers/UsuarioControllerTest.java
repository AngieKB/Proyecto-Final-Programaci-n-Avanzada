package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.DTO.Usuario.CrearUsuarioDTO;
import co.edu.uniquindio.Application.Repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @Sql("classpath:dataset.sql")
    void testCrearUsuarioExitoso() throws Exception {
        var usuario = new CrearUsuarioDTO(
                "Carlos",
                "carlos@email.com",
                "123456789",
                "Password123",
                LocalDate.of(1990, 1, 1)
        );

        mockMvc.perform(post("/api/usuario")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated());
    }

    @Test
    @Sql("classpath:dataset.sql")
    void testCrearUsuario_EmailRepetido() throws Exception {
        var usuario = new CrearUsuarioDTO(
                "Carlos",
                "juan@example.com",
                "123456789",
                "Password123",
                LocalDate.of(1990, 1, 1)
        );

        mockMvc.perform(post("/api/usuario")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isConflict());
    }

    @Test
    @Sql("classpath:dataset.sql")
    void testObtenerUsuarioExitoso() throws Exception {
        var usuarioGuardado = usuarioRepository.findAll().stream().findAny().orElseThrow();

        mockMvc.perform(get("/api/usuario/" + usuarioGuardado.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").value(false));
    }

    @Test
    @Sql("classpath:dataset.sql")
    void testObtenerUsuario_NoExiste() throws Exception {
        mockMvc.perform(get("/api/usuario/999"))
                .andExpect(status().isNotFound());
    }
}
