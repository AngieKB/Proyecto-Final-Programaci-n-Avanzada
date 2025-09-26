package co.edu.uniquindio.Application.Repository;

import co.edu.uniquindio.Application.Model.Alojamiento;
import co.edu.uniquindio.Application.Model.PerfilAnfitrion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfilAnfitrionRepository extends JpaRepository<PerfilAnfitrion, Long> {
    List<Alojamiento> findByAnfitrionId(Long anfitrionId);
}
