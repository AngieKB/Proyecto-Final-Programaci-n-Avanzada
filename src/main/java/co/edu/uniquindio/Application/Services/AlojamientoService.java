package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.Alojamiento.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface AlojamientoService {
    void guardar(CrearAlojamientoDTO alojamientodto) throws Exception;
    AlojamientoDTO obtenerPorId(Long id) throws Exception;
    List<AlojamientoDTO> listarTodos()throws Exception;
    void editarAlojamiento(Long id, AlojamientoDTO dto, UbicacionDTO ubicaciondto)throws Exception;
    void eliminar(Long id)throws Exception;
    MetricasDTO verMetricas(Long id, LocalDateTime fechamin, LocalDateTime fechamax)throws Exception;
    List<ResumenAlojamientoDTO> buscarPorCiudad(String ciudad)throws Exception;
    List<ResumenAlojamientoDTO> buscarPorPrecio(double min, double max)throws Exception;
    List<AlojamientoDTO> listarPorAnfitrion(Long idAnfitrion)throws Exception;
    List<ResumenAlojamientoDTO> buscarPorFechas(LocalDateTime inicio, LocalDateTime fin)throws Exception;
    List<AlojamientoDTO> buscarPorServicios(List<String> servicios)throws Exception;
}