package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.ComentarDTO;
import co.edu.uniquindio.Application.DTO.ComentarioDTO;

import java.util.List;

public interface ComentarioService {
    void comentar(ComentarDTO comentarDTO) throws Exception;
    List<ComentarioDTO> listarTodos() throws Exception;
    List<ComentarioDTO> listarComentariosPorAlojamiento(Long alojamientoId) throws Exception;
}
