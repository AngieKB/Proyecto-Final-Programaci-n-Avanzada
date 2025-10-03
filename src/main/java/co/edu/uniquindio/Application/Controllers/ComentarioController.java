package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.DTO.Comentario.ComentarDTO;
import co.edu.uniquindio.Application.DTO.Comentario.ComentarioDTO;
import co.edu.uniquindio.Application.DTO.ResponseDTO;
import co.edu.uniquindio.Application.Services.impl.ComentarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comentarios")
public class ComentarioController {
    private final ComentarioServiceImpl comentarioService;

    @PostMapping
    public ResponseEntity<ResponseDTO<String>> crearComentario(@RequestBody ComentarDTO comentarDto) throws Exception{
        comentarioService.comentar(comentarDto);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Comentario creado exitosamente"));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ComentarioDTO>>> obtenerComentariosPorAlojamiento(@RequestParam Long idAlojamiento) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, comentarioService.listarComentariosPorAlojamiento(idAlojamiento)));
    }
}