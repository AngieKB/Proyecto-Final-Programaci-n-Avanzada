package co.edu.uniquindio.Application.Repository;

import co.edu.uniquindio.Application.Model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
