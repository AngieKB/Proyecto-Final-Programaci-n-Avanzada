package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.Reserva.RealizarReservaDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaUsuarioDTO;
import co.edu.uniquindio.Application.Model.EstadoReserva;

import java.util.List;

public interface ReservaService {
    void guardar(RealizarReservaDTO dto) throws Exception;
    void editarReserva(Long id, ReservaDTO dto);
    List<ReservaUsuarioDTO> obtenerReservasPorId(Long id);
    void cancelarReserva(Long id);
    EstadoReserva obtenerEstadoReserva(Long id);
}
