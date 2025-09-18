package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.CrearHuespedDTO;
import co.edu.uniquindio.Application.DTO.EditarHuespedDTO;
import co.edu.uniquindio.Application.DTO.UsuarioDTO;
import co.edu.uniquindio.Application.Services.ServicioHuesped;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioHuespedImpl implements ServicioHuesped {
    @Override
    public void create(CrearHuespedDTO userDTO) throws Exception {

    }

    @Override
    public UsuarioDTO get(String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public List<UsuarioDTO> listAll() {
        return List.of();
    }

    @Override
    public void edit(String id, EditarHuespedDTO userDTO) throws Exception {

    }
}
