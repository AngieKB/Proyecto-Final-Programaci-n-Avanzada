package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.Reserva.RealizarReservaDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaDTO;
import co.edu.uniquindio.Application.Model.EstadoReserva;

public interface ReservaService {
    void guardar(RealizarReservaDTO dto) throws Exception;
    void editarReserva(Long id, ReservaDTO dto);
    ReservaDTO obtenerPorId(Long id);
    void cancelarReserva(Long id);
    EstadoReserva obtenerEstadoReserva(Long id);
}
