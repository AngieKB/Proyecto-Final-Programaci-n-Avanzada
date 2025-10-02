package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.CrearAlojamientoDTO;
import co.edu.uniquindio.Application.DTO.ReservaDTO;
import co.edu.uniquindio.Application.Model.Alojamiento;
import co.edu.uniquindio.Application.Model.EstadoReserva;
import co.edu.uniquindio.Application.Model.Reserva;
import co.edu.uniquindio.Application.Repository.ReservaRepository;
import co.edu.uniquindio.Application.Services.ReservaService;
import co.edu.uniquindio.Application.mappers.ReservaMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {
    private final ReservaMapper reservaMapper;
    private final ReservaRepository reservaRepository;

    @Override
    public void cancelarReserva(Long id) {
    }
    @Override
    public EstadoReserva obtenerEstadoReserva(Long id) {
        return null;
    }

    @Override
    public void editarReserva(Long id, ReservaDTO dto){}

    @Override
    public ReservaDTO obtenerPorId(Long id) {
        return null;
    }
    @Override
    public void guardar(ReservaDTO reservadto) throws Exception{
        Reserva newReserva = reservaMapper.toEntity(reservadto);
        newReserva.setEstado(EstadoReserva.PENDIENTE);
        reservaRepository.save(newReserva);
    }
}

