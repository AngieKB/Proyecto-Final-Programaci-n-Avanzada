package co.edu.uniquindio.Application.Repository;

import co.edu.uniquindio.Application.Model.EstadoReserva;
import co.edu.uniquindio.Application.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByHuespedId(long huespedId);
    List<Reserva> findByAlojamientoId(long alojamientoId);
    List<Reserva> findByEstado(EstadoReserva estado);

}
