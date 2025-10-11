package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.DTO.Usuario.*;
import co.edu.uniquindio.Application.DTO.ResponseDTO;
import co.edu.uniquindio.Application.Services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> edit(@PathVariable Long id,
                                                    @RequestParam String nombre,
                                                    @RequestParam String telefono,
                                                    @RequestPart(required = false)MultipartFile fotoUrl) throws Exception{
        EditarUsuarioDTO usuarioDTO = new EditarUsuarioDTO(nombre, telefono, fotoUrl);
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
        UsuarioDTO usuarioDTO = usuarioService.get(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, usuarioDTO));
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