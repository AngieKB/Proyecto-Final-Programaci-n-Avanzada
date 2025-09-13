package co.edu.uniquindio.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Huesped extends Usuario{
    @OneToMany(mappedBy = "huesped",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "huesped_favoritos",
            joinColumns = @JoinColumn(name = "huesped_id"),
            inverseJoinColumns = @JoinColumn(name = "alojamiento_id")
    )
    private List<Alojamiento> favoritos;
}

