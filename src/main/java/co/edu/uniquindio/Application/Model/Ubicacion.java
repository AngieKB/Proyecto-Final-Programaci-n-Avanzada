package co.edu.uniquindio.Application.Model;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Ubicacion {

    private String direccion;
    private String ciudad;
    private String pais;
    private Double latitud;
    private Double longitud;
}
