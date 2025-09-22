package co.edu.uniquindio.Application.Repository;

import co.edu.uniquindio.Application.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
