package co.edu.uniquindio.Application.Mappers;

import co.edu.uniquindio.Application.DTO.Reserva.RealizarReservaDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaAlojamientoDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaUsuarioDTO;
import co.edu.uniquindio.Application.Model.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservaMapper {
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    Reserva toEntity(RealizarReservaDTO dto);
    ReservaDTO toDTO(Reserva entity);
    @Mapping(source = "reserva.alojamiento.titulo", target = "alojamientoTitulo")
    @Mapping(source = "reserva.alojamiento.ubicacion.ciudad", target = "alojamientoCiudad")
    ReservaUsuarioDTO toUsuarioDTO(Reserva reserva);
    @Mapping(source = "reserva.alojamiento.titulo", target = "alojamientoTitulo")
    @Mapping(source = "reserva.alojamiento.ubicacion.ciudad", target = "alojamientoCiudad")
    @Mapping(source = "reserva.huesped.id", target = "idHuesped")
    ReservaAlojamientoDTO toAlojamientoDTO(Reserva reserva);

}
