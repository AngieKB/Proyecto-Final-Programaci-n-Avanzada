package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.PerfilAnfitrionDTO;
import co.edu.uniquindio.Application.Exceptions.RecursoNoEncontradoException;
import co.edu.uniquindio.Application.mappers.PerfilAnfitrionMapper;
import co.edu.uniquindio.Application.Model.PerfilAnfitrion;
import co.edu.uniquindio.Application.Repository.PerfilAnfitrionRepository;
import co.edu.uniquindio.Application.Services.PerfilAnfitrionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfilAnfitrionServiceImpl implements PerfilAnfitrionService {
    private final PerfilAnfitrionRepository repository;

    public PerfilAnfitrionServiceImpl(PerfilAnfitrionRepository repository) {
        this.repository = repository;
    }

    @Override
    public PerfilAnfitrionDTO crearPerfil(PerfilAnfitrionDTO dto) {
        PerfilAnfitrion entity = PerfilAnfitrionMapper.toEntity(dto);
        return PerfilAnfitrionMapper.toDTO(repository.save(entity));
    }

    @Override
    public PerfilAnfitrionDTO obtenerPerfil(Long id) {
        PerfilAnfitrion perfil = repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Perfil de anfitrión con id " + id + " no encontrado"));
        return PerfilAnfitrionMapper.toDTO(perfil);
    }

    @Override
    public List<PerfilAnfitrionDTO> listarPerfiles() {
        return repository.findAll()
                .stream()
                .map(PerfilAnfitrionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PerfilAnfitrionDTO actualizarPerfil(Long id, PerfilAnfitrionDTO dto) {
        PerfilAnfitrion perfil = repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Perfil de anfitrión con id " + id + " no encontrado"));

        perfil.setDescripcion(dto.descripcion());
        perfil.setDocumentosLegales(dto.domentosLegales());
        return PerfilAnfitrionMapper.toDTO(repository.save(perfil));
    }

    @Override
    public void eliminarPerfil(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNoEncontradoException("Perfil de anfitrión con id " + id + " no encontrado");
        }
        repository.deleteById(id);
    }
}
}
