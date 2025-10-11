package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.DTO.Anfitrion.EditarAnfitrionDTO;
import co.edu.uniquindio.Application.DTO.Anfitrion.PerfilAnfitrionDTO;
import co.edu.uniquindio.Application.DTO.ResponseDTO;
import co.edu.uniquindio.Application.Services.PerfilAnfitrionService;
import jakarta.validation.Valid;
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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<PerfilAnfitrionDTO>> obtenerPerfil(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseDTO<>(false, perfilAnfitrionService.obtenerPerfil(id)));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<PerfilAnfitrionDTO>>> listarPerfiles() {
        return ResponseEntity.ok(new ResponseDTO<>(false, perfilAnfitrionService.listarPerfiles()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> actualizarPerfil(@PathVariable Long id, @Valid @RequestBody EditarAnfitrionDTO dto) {
        perfilAnfitrionService.actualizarPerfil(id, dto);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Perfil actualizado exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> eliminarPerfil(@PathVariable Long id) {
        perfilAnfitrionService.eliminarPerfil(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Perfil eliminado exitosamente"));
    }
}