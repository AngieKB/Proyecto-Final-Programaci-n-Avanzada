package co.edu.uniquindio.Application.Mappers;

import co.edu.uniquindio.Application.DTO.Reserva.RealizarReservaDTO;
import co.edu.uniquindio.Application.DTO.Reserva.ReservaDTO;
import co.edu.uniquindio.Application.Model.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservaMapper {
    Reserva toEntity(RealizarReservaDTO dto);
    ReservaDTO toDTO(Reserva entity);
}
