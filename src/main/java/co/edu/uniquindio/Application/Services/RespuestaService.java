package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.Comentario.ResponderDTO;
import co.edu.uniquindio.Application.DTO.Comentario.RespuestaDTO;

public interface RespuestaService {
    void responderComentario(ResponderDTO dto) throws Exception;
    RespuestaDTO obtenerRespuestaPorComentario(Long idComentario);
    RespuestaDTO obtener(Long id);
}