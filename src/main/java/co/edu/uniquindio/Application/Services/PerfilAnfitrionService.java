package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.PerfilAnfitrionDTO;

import java.util.List;

public interface PerfilAnfitrionService {
    PerfilAnfitrionDTO crearPerfil(PerfilAnfitrionDTO dto);

    PerfilAnfitrionDTO obtenerPerfil(Long id);

    List<PerfilAnfitrionDTO> listarPerfiles();

    PerfilAnfitrionDTO actualizarPerfil(Long id, PerfilAnfitrionDTO dto);

    void eliminarPerfil(Long id);
}
