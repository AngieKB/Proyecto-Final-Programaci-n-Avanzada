package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.Alojamiento.*;
import co.edu.uniquindio.Application.Model.Alojamiento;
import co.edu.uniquindio.Application.Model.Comentario;
import co.edu.uniquindio.Application.Model.Ubicacion;
import co.edu.uniquindio.Application.Repository.AlojamientoRepository;
import co.edu.uniquindio.Application.Services.AlojamientoService;
import co.edu.uniquindio.Application.Services.ImageService;
import lombok.RequiredArgsConstructor;
import co.edu.uniquindio.Application.Mappers.AlojamientoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static co.edu.uniquindio.Application.Model.EstadoAlojamiento.ACTIVO;

@Service
@RequiredArgsConstructor
@Transactional
public class AlojamientoServiceImpl implements AlojamientoService {
    private final AlojamientoRepository alojamientoRepository;
    private final AlojamientoMapper alojamientoMapper;
    private final ImageService imageService;


    @Override
    public void guardar(CrearAlojamientoDTO dto) throws Exception {
        // Subir imágenes y obtener URLs
        List<String> urls = new ArrayList<>();
        for (MultipartFile imagen : dto.galeria()) {
            Map result = imageService.upload(imagen);
            urls.add(result.get("url").toString());  // guardamos la URL pública
        }
        Alojamiento alojamiento = alojamientoMapper.toEntity(dto);
        Ubicacion ubicacion = alojamientoMapper.crearUbicacion(dto);
        alojamiento.setGaleria(urls);
        alojamiento.setEstado(ACTIVO);
        alojamiento.setUbicacion(ubicacion);

        alojamientoRepository.save(alojamiento);
    }

    @Override
    public AlojamientoDTO obtenerPorId(Long id) throws Exception{
        return alojamientoRepository.findById(id).map(alojamientoMapper::toDTO).orElseThrow(() -> new Exception("Alojamiento no encontrado con id: " + id));
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
    public MetricasDTO verMetricas(Long id, LocalDateTime fechaMin, LocalDateTime fechaMax){
        Alojamiento alojamiento = alojamientoRepository.findById(id).orElseThrow(() -> new RuntimeException("Alojamiento no encontrado"));
        int total = alojamiento.getComentarios().stream().mapToInt(Comentario::getCalificacion).sum();
        double promedio = (double) total / alojamiento.getComentarios().size();
        int reservas = alojamiento.getReservas().size();
        return new MetricasDTO(promedio, reservas);
    }

    @Override
    public List<ResumenAlojamientoDTO> buscarPorCiudad(String ciudad) {
        return alojamientoRepository.findByUbicacionCiudadContainingIgnoreCaseAndEstado(ciudad,ACTIVO).stream().map(alojamientoMapper::toResumenDTO).toList();
    }

    @Override
    public List<AlojamientoDTO> listarPorAnfitrion(Long idAnfitrion) {
        return alojamientoRepository.findByAnfitrionId(idAnfitrion).stream().map(alojamientoMapper::toDTO).toList();
    }

    @Override
    public List<ResumenAlojamientoDTO> buscarPorPrecio(double min, double max) {
        return alojamientoRepository.findByPrecioNocheBetweenAndEstado(min,max,ACTIVO).stream().map(alojamientoMapper::toResumenDTO).toList();
    }

    @Override
    public List<ResumenAlojamientoDTO> buscarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return alojamientoRepository.findByDate(fechaInicio,fechaFin,ACTIVO).stream().map(alojamientoMapper::toResumenDTO).toList();
    }

    @Override
    public List<AlojamientoDTO> buscarPorServicios(List<String> servicios) {
        return alojamientoRepository.findByServicios(servicios, servicios.size())
                .stream()
                .map(alojamientoMapper::toDTO)
                .toList();
    }
}