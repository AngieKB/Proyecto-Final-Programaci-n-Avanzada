package co.edu.uniquindio.Application.Repository;

import co.edu.uniquindio.Application.Model.Comentario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ComentarioRepositoryTest {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Test
    @Sql("classpath:dataset.sql")
    void testFindByAlojamientoIdOrderByFechaDesc() {
        Long alojamientoId = 1L;

        List<Comentario> comentarios = comentarioRepository.findByAlojamientoIdOrderByFechaDesc(alojamientoId);

        assertThat(comentarios).isNotEmpty();
        assertThat(comentarios).hasSize(3);

        // Comprobar que están en orden descendente por fecha
        assertThat(comentarios.get(0).getFecha()).isAfterOrEqualTo(comentarios.get(1).getFecha());
        assertThat(comentarios.get(1).getFecha()).isAfterOrEqualTo(comentarios.get(2).getFecha());
    }

    @Test
    @Sql("classpath:dataset.sql")
    void testExistsByReservaId() {
        assertThat(comentarioRepository.existsByReservaId(4L)).isTrue();  // Ya comentada
        assertThat(comentarioRepository.existsByReservaId(2L)).isFalse(); // Aún sin comentario
    }

    @Test
    @Sql("classpath:dataset.sql")
    void testExistsByHuespedIdAndAlojamientoId() {
        // Amyi (id=1) ya comentó en reservas 2 y 5, pero 2 está pendiente de comentario
        assertThat(comentarioRepository.existsByHuespedIdAndAlojamientoId(1L, 1L)).isTrue();
        // Laura (id=3) comentó en reserva 4
        assertThat(comentarioRepository.existsByHuespedIdAndAlojamientoId(3L, 1L)).isTrue();
        // Huesped que nunca comentó
        assertThat(comentarioRepository.existsByHuespedIdAndAlojamientoId(99L, 1L)).isFalse();
    }

    @Test
    @Sql("classpath:dataset.sql")
    void testFindByAlojamientoId() {
        Long alojamientoId = 1L;

        List<Comentario> comentarios = comentarioRepository.findByAlojamientoId(alojamientoId);

        assertThat(comentarios).hasSize(3);
        assertThat(comentarios).extracting("texto")
                .containsExactlyInAnyOrder(
                        "Excelente lugar para descansar",
                        "Lugar acogedor, volvería de nuevo",
                        "Todo perfecto, recomiendo"
                );
    }
}
