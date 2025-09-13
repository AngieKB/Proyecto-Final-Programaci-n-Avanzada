package co.edu.uniquindio.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Huesped extends Usuario{
    private List<Alojamiento> favoritos;
}

