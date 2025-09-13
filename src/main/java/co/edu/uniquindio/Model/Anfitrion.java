package co.edu.uniquindio.Model;

import co.edu.uniquindio.Model.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Anfitrion extends Usuario{
    private String descripcion;
    private List<Alojamiento> alojamientos;
}
