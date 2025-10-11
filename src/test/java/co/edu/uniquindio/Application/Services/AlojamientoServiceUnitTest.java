package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.Alojamiento.*;
import co.edu.uniquindio.Application.Model.*;
import co.edu.uniquindio.Application.Repository.AlojamientoRepository;
import co.edu.uniquindio.Application.Services.impl.AlojamientoServiceImpl;
import co.edu.uniquindio.Application.Mappers.AlojamientoMapper;
import co.edu.uniquindio.Application.Services.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlojamientoServiceUnitTest {

    @InjectMocks
    private AlojamientoServiceImpl alojamientoService;

    @Mock
    private AlojamientoRepository alojamientoRepository;

    @Mock
    private AlojamientoMapper alojamientoMapper;

    @Mock
    private ImageService imageService;

    private Alojamiento alojamiento;
    private AlojamientoDTO alojamientoDTO;
    private UbicacionDTO ubicacionDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ubicacionDTO = new UbicacionDTO("Calle 123", "Bogotá", "Colombia", 4.7, -74.1);

        alojamientoDTO = new AlojamientoDTO(
                1L,
                "Hotel Central",
                "Descripción del hotel",
                List.of("WiFi", "Piscina"),
                List.of(),
                ubicacionDTO,
                200000.0,
                4,
                List.of(),
                List.of(),
                EstadoAlojamiento.ACTIVO
        );

        alojamiento = new Alojamiento();
        alojamiento.setId(1L);
        alojamiento.setTitulo("Hotel Central");
        alojamiento.setDescripcion("Descripción del hotel");
        alojamiento.setServicios(List.of("WiFi", "Piscina"));
        alojamiento.setGaleria(new ArrayList<>());
        alojamiento.setPrecioNoche(200000.0);
        alojamiento.setCapacidadMax(4);
        alojamiento.setEstado(EstadoAlojamiento.ACTIVO);
        alojamiento.setUbicacion(new Ubicacion(
                "Calle 123",
                "Bogotá",
                "Colombia",
                4.7,
                -74.1
        ));
        alojamiento.setReservas(new ArrayList<>());
        alojamiento.setComentarios(new ArrayList<>());
    }

    @Test
    void guardarExitoso() throws Exception {
        CrearAlojamientoDTO crearDTO = mock(CrearAlojamientoDTO.class);
        MultipartFile file = mock(MultipartFile.class);
        when(crearDTO.galeria()).thenReturn(List.of(file));
        when(imageService.upload(file)).thenReturn(Map.of("url", "http://image.com/img1.jpg"));
        when(alojamientoMapper.toEntity(crearDTO)).thenReturn(alojamiento);
        when(alojamientoMapper.crearUbicacion(crearDTO)).thenReturn(alojamiento.getUbicacion());

        alojamientoService.guardar(crearDTO);

        verify(alojamientoRepository, times(1)).save(alojamiento);
        assertEquals(List.of("http://image.com/img1.jpg"), alojamiento.getGaleria());
        assertEquals(EstadoAlojamiento.ACTIVO, alojamiento.getEstado());
    }

    @Test
    void editarAlojamientoExitoso() {
        when(alojamientoRepository.findById(1L)).thenReturn(Optional.of(alojamiento));

        alojamientoService.editarAlojamiento(1L, alojamientoDTO, ubicacionDTO);

        verify(alojamientoMapper, times(1)).updateEntity(alojamiento, alojamientoDTO);
        verify(alojamientoRepository, times(1)).save(alojamiento);
    }

    @Test
    void eliminarSinReservasFuturasExitoso() {
        when(alojamientoRepository.findById(1L)).thenReturn(Optional.of(alojamiento));

        alojamientoService.eliminar(1L);

        assertEquals(EstadoAlojamiento.INACTIVO, alojamiento.getEstado());
        verify(alojamientoRepository, times(1)).save(alojamiento);
    }

    @Test
    void eliminarConReservasFuturasLanzaExcepcion() {
        Reserva reservaFutura = new Reserva();
        reservaFutura.setFechaCheckIn(LocalDateTime.now().plusDays(5));
        reservaFutura.setEstado(EstadoReserva.PENDIENTE);
        alojamiento.setReservas(List.of(reservaFutura));

        when(alojamientoRepository.findById(1L)).thenReturn(Optional.of(alojamiento));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> alojamientoService.eliminar(1L));

        assertEquals("No se puede eliminar el alojamiento porque tiene reservas futuras.", exception.getMessage());
        verify(alojamientoRepository, never()).save(any());
    }

    @Test
    void verMetricasExitoso() {
        Comentario comentario = new Comentario();
        comentario.setCalificacion(4);
        comentario.setFecha(LocalDateTime.now());
        alojamiento.setComentarios(List.of(comentario));

        Reserva reserva = new Reserva();
        reserva.setFechaCheckIn(LocalDateTime.now().plusDays(1));
        reserva.setFechaCheckOut(LocalDateTime.now().plusDays(2));
        alojamiento.setReservas(List.of(reserva));

        when(alojamientoRepository.findById(1L)).thenReturn(Optional.of(alojamiento));

        MetricasDTO metrics = alojamientoService.verMetricas(1L, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(3));

        assertEquals(1, metrics.totalReservas());
        assertEquals(4.0, metrics.promedioCalificaciones());
    }


    @Test
    void obtenerPorIdExitoso() throws Exception {
        when(alojamientoRepository.findById(1L)).thenReturn(Optional.of(alojamiento));
        when(alojamientoMapper.toDTO(alojamiento)).thenReturn(alojamientoDTO);

        AlojamientoDTO dto = alojamientoService.obtenerPorId(1L);

        assertEquals(alojamientoDTO, dto);
    }
    @Test
    void buscarPorCiudadPredictiva() {
        ResumenAlojamientoDTO resumenDTO = mock(ResumenAlojamientoDTO.class);

        // Simular alojamientos que incluyen "Bogotá D.C."
        Alojamiento alojamientoBogotaDC = new Alojamiento();
        alojamientoBogotaDC.setUbicacion(new Ubicacion("Calle 1", "Bogotá D.C.", "Colombia", 4.7, -74.1));
        alojamientoBogotaDC.setEstado(EstadoAlojamiento.ACTIVO);

        when(alojamientoRepository.findByUbicacionCiudadContainingIgnoreCaseAndEstado("Bogotá", EstadoAlojamiento.ACTIVO))
                .thenReturn(List.of(alojamientoBogotaDC));
        when(alojamientoMapper.toResumenDTO(alojamientoBogotaDC)).thenReturn(resumenDTO);

        List<ResumenAlojamientoDTO> resultados = alojamientoService.buscarPorCiudad("Bogotá");

        assertEquals(1, resultados.size());
        assertEquals(resumenDTO, resultados.get(0));
    }

    @Test
    void buscarPorFechasDisponible() {
        ResumenAlojamientoDTO resumenDTO = mock(ResumenAlojamientoDTO.class);
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fin = inicio.plusDays(5);

        // Alojamiento sin reservas en el rango → disponible
        Alojamiento disponible = new Alojamiento();
        disponible.setReservas(new ArrayList<>());
        disponible.setEstado(EstadoAlojamiento.ACTIVO);

        when(alojamientoRepository.findByDate(inicio, fin, EstadoAlojamiento.ACTIVO))
                .thenReturn(List.of(disponible));
        when(alojamientoMapper.toResumenDTO(disponible)).thenReturn(resumenDTO);

        List<ResumenAlojamientoDTO> resultados = alojamientoService.buscarPorFechas(inicio, fin);

        assertEquals(1, resultados.size());
        assertEquals(resumenDTO, resultados.get(0));
    }

    @Test
    void buscarPorPrecioRango() {
        ResumenAlojamientoDTO resumenDTO = mock(ResumenAlojamientoDTO.class);

        Alojamiento hotel = new Alojamiento();
        hotel.setPrecioNoche(150.0);
        hotel.setEstado(EstadoAlojamiento.ACTIVO);

        when(alojamientoRepository.findByPrecioNocheBetweenAndEstado(50.0, 200.0, EstadoAlojamiento.ACTIVO))
                .thenReturn(List.of(hotel));
        when(alojamientoMapper.toResumenDTO(hotel)).thenReturn(resumenDTO);

        List<ResumenAlojamientoDTO> resultados = alojamientoService.buscarPorPrecio(50.0, 200.0);

        assertEquals(1, resultados.size());
        assertEquals(resumenDTO, resultados.get(0));
    }

    @Test
    void buscarPorServiciosExitoso() {
        List<String> servicios = List.of("WiFi", "Piscina");
        Alojamiento hotel = new Alojamiento();
        hotel.setServicios(List.of("WiFi", "Piscina", "Mascotas"));
        hotel.setEstado(EstadoAlojamiento.ACTIVO);

        when(alojamientoRepository.findByServicios(servicios, servicios.size()))
                .thenReturn(List.of(hotel));
        when(alojamientoMapper.toDTO(hotel)).thenReturn(alojamientoDTO);

        List<AlojamientoDTO> resultados = alojamientoService.buscarPorServicios(servicios);

        assertEquals(1, resultados.size());
        assertEquals(alojamientoDTO, resultados.get(0));
    }

}