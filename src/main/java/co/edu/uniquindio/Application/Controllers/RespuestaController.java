package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.DTO.Comentario.ResponderDTO;
import co.edu.uniquindio.Application.DTO.Comentario.RespuestaDTO;
import co.edu.uniquindio.Application.DTO.ResponseDTO;
import co.edu.uniquindio.Application.Services.impl.RespuestaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RespuestaController {
    private final RespuestaServiceImpl respuestaService;

    @PreAuthorize("hasRole('ANFITRION')")
    @PostMapping
    public ResponseEntity<ResponseDTO<String>> responderComentario(@RequestBody ResponderDTO responderDTO) throws Exception {
        respuestaService.responderComentario(responderDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false,"Respuesta enviada con exito"));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<RespuestaDTO>> obtenerRespuestaPorComentario(Long idComentario) {
        RespuestaDTO respuestaDTO = respuestaService.obtenerRespuestaPorComentario(idComentario);
        return ResponseEntity.ok(new ResponseDTO<>(false, respuestaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<RespuestaDTO>> obtenerRespuestaPorId(@PathVariable Long id) throws Exception {
        RespuestaDTO respuestaDTO = respuestaService.obtener(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, respuestaDTO));
    }
}