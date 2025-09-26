package co.edu.uniquindio.Application.Repository;


import co.edu.uniquindio.Application.Model.Alojamiento;
import co.edu.uniquindio.Application.Model.EstadoAlojamiento;
import co.edu.uniquindio.Application.Model.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlojamientoRepository extends JpaRepository<Alojamiento, Long> {

    List<Alojamiento> findByEstado(EstadoAlojamiento estado);
    List<Alojamiento> findByPrecioNocheBetween(Double precioMin, Double precioMax);
    List<Alojamiento> findByUbicacionCiudadContainingIgnoreCase(String ciudad);

    //no se si quitarlo, además de que eso, oarece trabalenguas como dijo el profe, no se si ponerlo como query
    List<Alojamiento> findByUbicacionCiudadContainingIgnoreCaseAndPrecioNocheBetweenAndEstado(
        String ciudad, Double precioMin, Double precioMax, EstadoAlojamiento estado);

}
