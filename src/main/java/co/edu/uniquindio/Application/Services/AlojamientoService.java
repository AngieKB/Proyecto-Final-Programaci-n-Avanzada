package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.AlojamientoDTO;
import co.edu.uniquindio.Application.DTO.UbicacionDTO;
import co.edu.uniquindio.Application.Model.Alojamiento;
import co.edu.uniquindio.Application.Model.Ubicacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface AlojamientoService {
    void guardar(AlojamientoDTO alojamientodto) throws Exception;
    Optional<AlojamientoDTO> obtenerPorId(Long id) throws Exception;
    List<AlojamientoDTO> listarTodos()throws Exception;
    void editarAlojamiento(Long id, AlojamientoDTO dto, UbicacionDTO ubicaciondto)throws Exception;
    void eliminar(Long id)throws Exception;
    List<String> verMetricas(Long id, LocalDateTime fechamin, LocalDateTime fechamax)throws Exception;
    List<AlojamientoDTO> buscarPorCiudad(String ciudad)throws Exception;
    List<AlojamientoDTO> buscarPorPrecio(double min, double max)throws Exception;
    List<AlojamientoDTO> listarPorAnfitrion(Long idAnfitrion)throws Exception;
    List<AlojamientoDTO> buscarPorFechas(LocalDateTime inicio, LocalDateTime fin)throws Exception;
    List<AlojamientoDTO> buscarPorServicios(List<String> servicios)throws Exception;




}

