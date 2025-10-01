package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.ComentarDTO;
import co.edu.uniquindio.Application.DTO.ComentarioDTO;
import co.edu.uniquindio.Application.Repository.ComentarioRepository;
import co.edu.uniquindio.Application.Services.ComentarioService;
import co.edu.uniquindio.Application.mappers.ComentarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    public void comentar(ComentarDTO comentarDTO) throws Exception {
        // Implementacion
    }

    public List<ComentarioDTO> listarTodos() throws Exception {
        return List<ComentarioDTO>(co.toDto(comentarioRepository.findAll());)
    }

    public List<ComentarioDTO> listarComentariosPorAlojamiento(Long alojamientoId) throws Exception {
        return comentarioMapper.toDto(comentarioRepository.findByAlojamientoId(alojamientoId));
    }
}
