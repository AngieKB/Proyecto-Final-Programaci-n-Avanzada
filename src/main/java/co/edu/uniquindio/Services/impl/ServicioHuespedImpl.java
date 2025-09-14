package co.edu.uniquindio.Services.impl;

import co.edu.uniquindio.DTO.CrearHuespedDTO;
import co.edu.uniquindio.DTO.EditarHuespedDTO;
import co.edu.uniquindio.DTO.HuespedDTO;
import co.edu.uniquindio.Services.ServicioHuesped;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioHuespedImpl implements ServicioHuesped {
    @Override
    public void create(CrearHuespedDTO userDTO) throws Exception {

    }

    @Override
    public HuespedDTO get(String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public List<HuespedDTO> listAll() {
        return List.of();
    }

    @Override
    public void edit(String id, EditarHuespedDTO userDTO) throws Exception {

    }
}
