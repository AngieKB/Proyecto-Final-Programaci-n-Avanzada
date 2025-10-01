package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.DTO.PerfilAnfitrionDTO;
import co.edu.uniquindio.Application.Services.PerfilAnfitrionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles-anfitrion")
public class PerfilAnfitrionController {

    private final PerfilAnfitrionService perfilAnfitrionService;

    public PerfilAnfitrionController(PerfilAnfitrionService perfilAnfitrionService) {
        this.perfilAnfitrionService = perfilAnfitrionService;
    }

    // Crear un nuevo perfil de anfitrión
    @PostMapping
    public ResponseEntity<PerfilAnfitrionDTO> crearPerfil(@RequestBody PerfilAnfitrionDTO dto) {
        PerfilAnfitrionDTO nuevo = perfilAnfitrionService.crearPerfil(dto);
        return ResponseEntity.ok(nuevo);
    }

    // Obtener perfil por ID
    @GetMapping("/{id}")
    public ResponseEntity<PerfilAnfitrionDTO> obtenerPerfil(@PathVariable Long id) {
        return ResponseEntity.ok(perfilAnfitrionService.obtenerPerfil(id));
    }

    // Listar todos los perfiles
    @GetMapping
    public ResponseEntity<List<PerfilAnfitrionDTO>> listarPerfiles() {
        return ResponseEntity.ok(perfilAnfitrionService.listarPerfiles());
    }

    // Actualizar perfil
    @PutMapping("/{id}")
    public ResponseEntity<PerfilAnfitrionDTO> actualizarPerfil(
            @PathVariable Long id,
            @RequestBody PerfilAnfitrionDTO dto
    ) {
        return ResponseEntity.ok(perfilAnfitrionService.actualizarPerfil(id, dto));
    }

    // Eliminar perfil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPerfil(@PathVariable Long id) {
        perfilAnfitrionService.eliminarPerfil(id);
        return ResponseEntity.noContent().build();
    }
}