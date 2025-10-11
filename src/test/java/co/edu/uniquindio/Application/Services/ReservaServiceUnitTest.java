package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.EmailDTO;
import co.edu.uniquindio.Application.DTO.Reserva.EditarReservaDTO;
import co.edu.uniquindio.Application.DTO.Reserva.RealizarReservaDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaAlojamientoDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaUsuarioDTO;
import co.edu.uniquindio.Application.Mappers.ReservaMapper;
import co.edu.uniquindio.Application.Model.*;
import co.edu.uniquindio.Application.Repository.AlojamientoRepository;
import co.edu.uniquindio.Application.Repository.ReservaRepository;
import co.edu.uniquindio.Application.Repository.UsuarioRepository;
import co.edu.uniquindio.Application.Services.impl.ReservaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceUnitTest {

    @Mock private ReservaMapper reservaMapper;
    @Mock private ReservaRepository reservaRepository;
    @Mock private UsuarioRepository usuarioRepository;
    @Mock private AlojamientoRepository alojamientoRepository;
    @Mock private EmailService emailService;

    @InjectMocks
    private ReservaServiceImpl reservaService;

    private Usuario huesped;
    private Alojamiento alojamiento;
    private Reserva reserva;
    private Ubicacion ubicacion;

    @BeforeEach
    void setUp() {
        huesped = new Usuario();
        huesped.setId(1L);
        huesped.setNombre("Juan");
        huesped.setEmail("juan@mail.com");

        Usuario anfitrionUsuario = new Usuario();
        anfitrionUsuario.setId(2L);
        anfitrionUsuario.setEmail("anfitrion@mail.com");

        PerfilAnfitrion perfilAnfitrion = new PerfilAnfitrion();
        perfilAnfitrion.setUsuario(anfitrionUsuario);

        ubicacion = new Ubicacion();
        ubicacion.setCiudad("Armenia");
        ubicacion.setPais("Colombia");
        ubicacion.setLatitud(4.5339);
        ubicacion.setLongitud(-75.6811);

        alojamiento = new Alojamiento();
        alojamiento.setId(10L);
        alojamiento.setTitulo("Cabaña del Bosque");
        alojamiento.setCapacidadMax(4);
        alojamiento.setPrecioNoche(100.0);
        alojamiento.setAnfitrion(perfilAnfitrion);
        alojamiento.setUbicacion(ubicacion);

        reserva = new Reserva();
        reserva.setId(99L);
        reserva.setHuesped(huesped);
        reserva.setAlojamiento(alojamiento);
        reserva.setFechaCheckIn(LocalDateTime.now().plusDays(3));
        reserva.setFechaCheckOut(LocalDateTime.now().plusDays(5));
        reserva.setEstado(EstadoReserva.PENDIENTE);
        reserva.setCantidadHuespedes(2);
    }

    // === TEST CANCELAR RESERVA EXITOSO ===
    @Test
    void testCancelarReservaExitosa() throws Exception {
        when(reservaRepository.findById(reserva.getId())).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        assertDoesNotThrow(() -> reservaService.cancelarReserva(reserva.getId()));

        assertEquals(EstadoReserva.CANCELADA, reserva.getEstado());
        verify(reservaRepository, times(1)).save(reserva);
        verify(emailService, times(2)).sendMail(any(EmailDTO.class));
    }

    // === TEST CANCELAR RESERVA YA CANCELADA ===
    @Test
    void testCancelarReservaYaCancelada() {
        reserva.setEstado(EstadoReserva.CANCELADA);
        when(reservaRepository.findById(reserva.getId())).thenReturn(Optional.of(reserva));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> reservaService.cancelarReserva(reserva.getId()));
        assertEquals("La reserva ya se encuentra cancelada.", ex.getMessage());
    }

    // === TEST CANCELAR RESERVA MENOS DE 48 HORAS ===
    @Test
    void testCancelarReservaMenosDe48HorasAntes() {
        reserva.setFechaCheckIn(LocalDateTime.now().plusHours(30));
        when(reservaRepository.findById(reserva.getId())).thenReturn(Optional.of(reserva));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> reservaService.cancelarReserva(reserva.getId()));
        assertTrue(ex.getMessage().contains("No se puede cancelar la reserva"));
    }

    // === TEST CANCELAR RESERVA NO EXISTE ===
    @Test
    void testCancelarReservaNoExiste() {
        when(reservaRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> reservaService.cancelarReserva(999L));
    }

    // === TEST GUARDAR RESERVA EXITOSA ===
    @Test
    void testGuardarReservaExitosa() throws Exception {
        RealizarReservaDTO dto = new RealizarReservaDTO(
                reserva.getFechaCheckIn(),
                reserva.getFechaCheckOut(),
                2,
                huesped.getId(),
                alojamiento.getId()


        );

        when(usuarioRepository.findById(huesped.getId())).thenReturn(Optional.of(huesped));
        when(alojamientoRepository.findById(alojamiento.getId())).thenReturn(Optional.of(alojamiento));

        when(reservaMapper.toEntity(dto)).thenReturn(reserva);
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);
        doNothing().when(emailService).sendMail(any(EmailDTO.class));

        assertDoesNotThrow(() -> reservaService.guardar(dto));
        verify(reservaRepository, times(1)).save(any(Reserva.class));
        verify(emailService, times(2)).sendMail(any(EmailDTO.class));
    }

    // === TEST GUARDAR RESERVA CON FECHAS PASADAS ===
    @Test
    void testGuardarReservaFechasPasadas() {
        RealizarReservaDTO dto = new RealizarReservaDTO(
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1),
                2,
                huesped.getId(),
                alojamiento.getId()


        );

        when(usuarioRepository.findById(huesped.getId())).thenReturn(Optional.of(huesped));
        when(alojamientoRepository.findById(alojamiento.getId())).thenReturn(Optional.of(alojamiento));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> reservaService.guardar(dto));
        assertEquals("No se pueden reservar fechas pasadas.", ex.getMessage());
    }

    // === TEST GUARDAR RESERVA MENOS DE UNA NOCHE ===
    @Test
    void testGuardarReservaMenosDeUnaNoche() {
        RealizarReservaDTO dto = new RealizarReservaDTO(
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusHours(12), // menos de 1 noche
                2, // cantidad de hué
                1L, // id huésped
                1L // id alojamiento

        );

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(huesped));
        when(alojamientoRepository.findById(1L)).thenReturn(Optional.of(alojamiento));
        alojamiento.setCapacidadMax(5);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            reservaService.guardar(dto);
        });

        assertEquals("La reserva debe ser mínimo de 1 noche.", exception.getMessage());
    }


    // === TEST GUARDAR RESERVA SUPERA CAPACIDAD ===
    @Test
    void testGuardarReservaSuperaCapacidad() {
        RealizarReservaDTO dto = new RealizarReservaDTO(
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(3),
                10, // cantidad de huéspedes mayor a capacidad
                1L, // id huésped
                1L// id alojamiento

        );

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(huesped));
        when(alojamientoRepository.findById(1L)).thenReturn(Optional.of(alojamiento));
        alojamiento.setCapacidadMax(5); // por ejemplo

        Exception exception = assertThrows(RuntimeException.class, () -> {
            reservaService.guardar(dto);
        });

        assertEquals("Se supera la capacidad máxima del alojamiento.", exception.getMessage());
    }


    // === TEST EDITAR RESERVA EXITOSA ===
    @Test
    void testEditarReservaExitosa() {
        EditarReservaDTO dto = new EditarReservaDTO(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(4), 3,500.0);
        when(reservaRepository.findById(reserva.getId())).thenReturn(Optional.of(reserva));

        assertDoesNotThrow(() -> reservaService.editarReserva(reserva.getId(), dto));
        verify(reservaMapper, times(1)).updateReservaFromDTO(dto, reserva);
        verify(reservaRepository, times(1)).save(reserva);
    }

    // === TEST EDITAR RESERVA CANCELADA ===
    @Test
    void testEditarReservaCancelada() {
        reserva.setEstado(EstadoReserva.CANCELADA);
        when(reservaRepository.findById(reserva.getId())).thenReturn(Optional.of(reserva));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> reservaService.editarReserva(reserva.getId(), new EditarReservaDTO(null, null, 0,null)));
        assertTrue(ex.getMessage().contains("cancelada"));
    }

    // === TEST OBTENER RESERVAS POR HUESPED ===
    @Test
    void testObtenerReservasPorHuesped() {
        when(reservaRepository.findByHuespedId(huesped.getId())).thenReturn(List.of(reserva));

        ReservaUsuarioDTO dto = new ReservaUsuarioDTO(
                reserva.getId(),
                reserva.getFechaCheckIn(),
                reserva.getFechaCheckOut(),
                reserva.getCantidadHuespedes(),
                reserva.getTotal(),
                reserva.getEstado(),
                reserva.getAlojamiento().getTitulo(),
                reserva.getAlojamiento().getUbicacion().getCiudad()
        );

        when(reservaMapper.toUsuarioDTO(reserva)).thenReturn(dto);

        List<ReservaUsuarioDTO> result = reservaService.obtenerReservasPorIdHuesped(huesped.getId());

        assertEquals(1, result.size());
        assertEquals("Cabaña del Bosque", result.get(0).alojamientoTitulo());
        verify(reservaRepository, times(1)).findByHuespedId(huesped.getId());
    }


    // === TEST OBTENER RESERVAS POR ALOJAMIENTO ===
    @Test
    void testObtenerReservasPorAlojamiento() {
        when(reservaRepository.findByAlojamientoId(alojamiento.getId())).thenReturn(List.of(reserva));

        ReservaAlojamientoDTO dto = new ReservaAlojamientoDTO(
                reserva.getId(),
                reserva.getHuesped().getId(),
                reserva.getFechaCheckIn(),
                reserva.getFechaCheckOut(),
                reserva.getCantidadHuespedes(),
                reserva.getTotal(),
                reserva.getEstado(),
                reserva.getAlojamiento().getTitulo(),
                reserva.getAlojamiento().getUbicacion().getCiudad()
        );

        when(reservaMapper.toAlojamientoDTO(reserva)).thenReturn(dto);

        List<ReservaAlojamientoDTO> result = reservaService.obtenerReservasPorIdAlojamiento(alojamiento.getId());

        assertEquals(1, result.size());
        assertEquals(reserva.getHuesped().getId(), result.get(0).idHuesped());
        verify(reservaRepository, times(1)).findByAlojamientoId(alojamiento.getId());
    }

}
