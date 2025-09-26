package co.edu.uniquindio.Application.Repository;

import co.edu.uniquindio.Application.Model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    List<Respuesta> findByComentarioId(Long comentarioId);
}
