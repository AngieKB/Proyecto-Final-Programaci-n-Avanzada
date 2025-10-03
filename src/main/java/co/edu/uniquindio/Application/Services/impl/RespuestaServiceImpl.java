package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.Comentario.ComentarioDTO;
import co.edu.uniquindio.Application.DTO.Comentario.ResponderDTO;
import co.edu.uniquindio.Application.DTO.Comentario.RespuestaDTO;
import co.edu.uniquindio.Application.Mappers.RespuestaMapper;
import co.edu.uniquindio.Application.Repository.RespuestaRepository;
import co.edu.uniquindio.Application.Services.RespuestaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespuestaServiceImpl implements RespuestaService {
    private final RespuestaRepository respuestaRepository;
    private final RespuestaMapper respuestaMapper;

    @Override
    public void responderComentario(ResponderDTO dto) throws Exception {
        respuestaRepository.save(respuestaMapper.toEntity(dto));
    }

    @Override
    public RespuestaDTO obtenerRespuestaPorComentario(Long idComentario) {
        return respuestaMapper.toDTO(respuestaRepository.findByComentarioId(idComentario));
    }

    @Override
    public RespuestaDTO obtener(Long id) {
        return respuestaMapper.toDTO(respuestaRepository.findById(id).orElse(null));
    }
}