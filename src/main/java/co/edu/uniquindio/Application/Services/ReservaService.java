package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.Model.EstadoReserva;

public interface ReservaService {
    void cancelarReserva(Long id);
    EstadoReserva obtenerEstadoReserva(Long id);
}
