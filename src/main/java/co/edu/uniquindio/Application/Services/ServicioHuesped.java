package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.CrearHuespedDTO;
import co.edu.uniquindio.Application.DTO.EditarHuespedDTO;
import co.edu.uniquindio.Application.DTO.HuespedDTO;

import java.util.List;

public interface ServicioHuesped {
    void create(CrearHuespedDTO userDTO) throws Exception;

    HuespedDTO get(String id) throws Exception;

    void delete(String id) throws Exception;

    List<HuespedDTO> listAll();

    void edit(String id, EditarHuespedDTO userDTO) throws Exception;
}
