package co.edu.uniquindio.Application.Repository;

import co.edu.uniquindio.Application.Model.Respuesta;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    Respuesta findByComentarioId(Long comentarioId);

    boolean existsByComentarioId(@NotNull Long aLong);
}
