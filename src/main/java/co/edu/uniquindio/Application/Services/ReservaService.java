package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.ReservaDTO;
import co.edu.uniquindio.Application.Model.EstadoReserva;

public interface ReservaService {
    void guardar(ReservaDTO dto) throws Exception;
    void editarReserva(Long id, ReservaDTO dto);
    ReservaDTO obtenerPorId(Long id);
    void cancelarReserva(Long id);
    EstadoReserva obtenerEstadoReserva(Long id);
}
