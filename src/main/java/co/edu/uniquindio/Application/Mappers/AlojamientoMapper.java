package co.edu.uniquindio.Application.Mappers;

import co.edu.uniquindio.Application.DTO.Alojamiento.AlojamientoDTO;
import co.edu.uniquindio.Application.DTO.Alojamiento.CrearAlojamientoDTO;
import co.edu.uniquindio.Application.DTO.Alojamiento.ResumenAlojamientoDTO;
import co.edu.uniquindio.Application.Model.Alojamiento;
import co.edu.uniquindio.Application.Model.Ubicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlojamientoMapper {
    @Mapping(target = "galeria", ignore = true)
    Alojamiento toEntity(CrearAlojamientoDTO dto);
    AlojamientoDTO toDTO(Alojamiento entity);
    default Ubicacion crearUbicacion(CrearAlojamientoDTO dto) {
        return new Ubicacion(
                dto.direccion(),
                dto.ciudad(),
                dto.pais(),
                dto.latitud(),
                dto.longitud()
        );
    }
    @Mapping(target = "imagenPrincipal", expression = "java(getPrimeraFoto(entity))")
    @Mapping(target = "ciudad", source = "ubicacion.ciudad")
    ResumenAlojamientoDTO toResumenDTO(Alojamiento entity);
    default String getPrimeraFoto(Alojamiento entity) {
        if (entity.getGaleria() != null && !entity.getGaleria().isEmpty()) {
            return entity.getGaleria().getFirst(); // primera foto
        }
        return null;
    }

}
