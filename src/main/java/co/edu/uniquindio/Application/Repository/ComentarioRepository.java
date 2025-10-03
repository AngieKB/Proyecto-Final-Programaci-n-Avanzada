package co.edu.uniquindio.Application.Repository;

import co.edu.uniquindio.Application.Model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByAlojamientoIdOrderByFechaDesc(Long alojamientoId);
    boolean existsByAlojamientoIdAndHuespedId(Long alojamientoId, Long huespedId);
    List<Comentario> findByAlojamientoId(Long alojamientoId);
}
