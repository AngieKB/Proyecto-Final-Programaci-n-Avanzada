package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.AlojamientoDTO;
import co.edu.uniquindio.Application.DTO.UbicacionDTO;
import co.edu.uniquindio.Application.Model.Alojamiento;
import co.edu.uniquindio.Application.Model.Ubicacion;
import co.edu.uniquindio.Application.Repository.AlojamientoRepository;
import co.edu.uniquindio.Application.Services.AlojamientoService;
import lombok.RequiredArgsConstructor;
import co.edu.uniquindio.Application.mappers.AlojamientoMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlojamientoServiceImpl implements AlojamientoService {
    private final AlojamientoRepository alojamientoRepository;
    private final AlojamientoMapper alojamientoMapper;


    @Override
    public void guardar(AlojamientoDTO alojamientodto) throws Exception{
        Alojamiento newAlojamiento = alojamientoMapper.toEntity(alojamientodto);
        alojamientoRepository.save(newAlojamiento);
    }

    @Override
    public Optional<AlojamientoDTO> obtenerPorId(Long id) throws Exception{
        return alojamientoRepository.findById(id).map(alojamientoMapper::toDTO);
    }

    @Override
    public void editarAlojamiento(Long id, AlojamientoDTO alojadto, UbicacionDTO ubicaciondto){
        Alojamiento alojamiento = alojamientoRepository.findById(id).orElseThrow(() -> new RuntimeException("Alojamiento no encontrado con id: " + id));
        Ubicacion ubicacion = alojamiento.getUbicacion();
        ubicacion.setCiudad(ubicaciondto.ciudad());
        ubicacion.setDireccion(ubicaciondto.direccion());
        ubicacion.setPais(ubicaciondto.pais());
        ubicacion.setLatitud(ubicaciondto.latitud());
        ubicacion.setLongitud(ubicaciondto.longitud());
        alojamiento.setEstado(alojadto.estado());
        alojamiento.setDescripcion(alojadto.descripcion());
        alojamiento.setCapacidadMax(alojadto.capacidadMax());
        alojamiento.setPrecioNoche(alojadto.precioNoche());
        alojamiento.setServicios(alojadto.servicios());
        alojamiento.setTitulo(alojadto.titulo());
        alojamiento.setUbicacion(ubicacion);
        alojamientoRepository.save(alojamiento);
    }

    @Override
    public List<AlojamientoDTO> listarTodos() {
        return alojamientoRepository.findAll().stream().map(alojamientoMapper::toDTO).toList() ;
    }

    @Override
    public void eliminar(Long id) {
        alojamientoRepository.deleteById(id);
    }
    @Override
    public List<String> verMetricas(Long id, LocalDateTime fechaMin, LocalDateTime fechaMax){
        Alojamiento alojamiento = alojamientoRepository.findById(id).orElseThrow(() -> new RuntimeException("Alojamiento no encontrado"));
        List<String> metricas = alojamiento.verMetricas();
        return metricas;
    }
    @Override
    public List<AlojamientoDTO> buscarPorCiudad(String ciudad) {
        return alojamientoRepository.findByCity().stream().map(alojamientoMapper::toDTO).toList();
    }
    @Override
    public List<AlojamientoDTO> listarPorAnfitrion(Long idAnfitrion) {
        return alojamientoRepository.findByHost().stream().map(alojamientoMapper::toDTO).toList();
    }
    @Override
    public List<AlojamientoDTO> buscarPorPrecio(double min, double max) {
        return alojamientoRepository.findByPrice().stream().map(alojamientoMapper::toDTO).toList();
    }

    @Override
    public List<AlojamientoDTO> buscarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return alojamientoRepository.findByDate().stream().map(alojamientoMapper::toDTO).toList();
    }
    @Override
    public List<AlojamientoDTO> buscarPorServicios(List<String> servicios) {
        return alojamientoRepository.findByServices().stream().map(alojamientoMapper::toDTO).toList();
    }
}