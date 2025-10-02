package co.edu.uniquindio.Application.mappers;

import co.edu.uniquindio.Application.DTO.ReservaDTO;
import co.edu.uniquindio.Application.Model.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservaMapper {
    Reserva toEntity(ReservaDTO dto);
    ReservaDTO toDTO(Reserva entity);
}
