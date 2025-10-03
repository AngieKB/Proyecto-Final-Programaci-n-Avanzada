package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.Comentario.ComentarDTO;
import co.edu.uniquindio.Application.DTO.Comentario.ComentarioDTO;
import co.edu.uniquindio.Application.Model.Comentario;
import co.edu.uniquindio.Application.Repository.ComentarioRepository;
import co.edu.uniquindio.Application.Services.ComentarioService;
import co.edu.uniquindio.Application.Mappers.ComentarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    public void comentar(ComentarDTO comentarDTO) throws Exception {
        comentarioRepository.save(comentarioMapper.toEntity(comentarDTO));
    }

    public List<ComentarioDTO> listarComentariosPorAlojamiento(Long alojamientoId) throws Exception {
        List<Comentario> comentarios = comentarioRepository.findByAlojamientoId(alojamientoId);
        List<ComentarioDTO> comentarioDTOS = new ArrayList<>();
        for (Comentario comentario : comentarios) {
            comentarioDTOS.add(comentarioMapper.toDto(comentario));
        }
        return comentarioDTOS;
    }
}
