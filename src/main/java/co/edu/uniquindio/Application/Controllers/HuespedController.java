package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.DTO.CrearHuespedDTO;
import co.edu.uniquindio.Application.DTO.EditarHuespedDTO;
import co.edu.uniquindio.Application.DTO.HuespedDTO;
import co.edu.uniquindio.Application.DTO.ResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/huesped")
public class HuespedController {

    @PostMapping
    public ResponseEntity<ResponseDTO<String>> create(@Valid @RequestBody CrearHuespedDTO huespedDTO) throws Exception{
        //Lógica para crear el huesped
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El registro ha sido exitoso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> edit(@PathVariable String id, @Valid @RequestBody EditarHuespedDTO userDTO) throws Exception{
        //Lógica para editar el huesped
        return ResponseEntity.ok(new ResponseDTO<>(false, "El huesped ha sido actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> delete(@PathVariable String id) throws Exception{
        //Lógica para eliminar el huesped
        return ResponseEntity.ok(new ResponseDTO<>(false, "El huesped ha sido eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<HuespedDTO>> get(@PathVariable String id) throws Exception{
        //Lógica para consular el huesped
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<HuespedDTO>>> listAll(){
        //Lógica para consultar todos los huespedes
        List<HuespedDTO> list = new ArrayList<>();
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }
}