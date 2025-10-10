package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.DTO.Usuario.*;
import co.edu.uniquindio.Application.DTO.ResponseDTO;
import co.edu.uniquindio.Application.Services.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> edit(@PathVariable Long id, @Valid @RequestBody EditarUsuarioDTO usuarioDTO) throws Exception{
        usuarioService.edit(id, usuarioDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> delete(@PathVariable Long id) throws Exception{
        usuarioService.delete(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UsuarioDTO>> get(@PathVariable Long id) throws Exception{
        usuarioService.get(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<UsuarioDTO>>> listAll(){
        List<UsuarioDTO> list = new ArrayList<>(usuarioService.listAll());
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }

    @PutMapping("/{id}/cambiar-password")
    public ResponseEntity<ResponseDTO<String>> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO, @PathVariable Long id) throws Exception{
        usuarioService.changePassword(id, changePasswordDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Contraseña actualizada exitosamente"));
    }
}