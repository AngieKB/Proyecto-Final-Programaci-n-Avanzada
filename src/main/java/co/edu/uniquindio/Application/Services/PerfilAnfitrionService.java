package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.Anfitrion.CrearAnfitrionDTO;
import co.edu.uniquindio.Application.DTO.Anfitrion.EditarAnfitrionDTO;
import co.edu.uniquindio.Application.DTO.Anfitrion.PerfilAnfitrionDTO;

import java.util.List;
import java.util.Optional;

public interface PerfilAnfitrionService {
    void crearPerfil(CrearAnfitrionDTO dto);

    PerfilAnfitrionDTO obtenerPerfil(Long id);

    List<PerfilAnfitrionDTO> listarPerfiles();

    void actualizarPerfil(Long id, EditarAnfitrionDTO dto);

    void eliminarPerfil(Long id);
}
