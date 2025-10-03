package co.edu.uniquindio.Application.Controllers;


import co.edu.uniquindio.Application.DTO.*;
import co.edu.uniquindio.Application.DTO.Alojamiento.AlojamientoDTO;
import co.edu.uniquindio.Application.DTO.Alojamiento.CrearAlojamientoDTO;
import co.edu.uniquindio.Application.DTO.Alojamiento.UbicacionDTO;
import co.edu.uniquindio.Application.Services.impl.AlojamientoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alojamiento")
public class AlojamientoController {

    private final AlojamientoServiceImpl alojamientoService;

    @PostMapping
    public ResponseEntity<ResponseDTO<String>> crear(@Valid @RequestBody CrearAlojamientoDTO alojamientoDTO) throws Exception {
        alojamientoService.guardar(alojamientoDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido registrado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> editar(@PathVariable Long id, @Valid @RequestBody AlojamientoDTO alojamientoDTO, @Valid@RequestBody UbicacionDTO ubicacionDTO) throws Exception{
        alojamientoService.editarAlojamiento(id, alojamientoDTO, ubicacionDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> eliminar(@PathVariable Long id) throws Exception{
        alojamientoService.eliminar(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<AlojamientoDTO>> obtenerPorId(@PathVariable Long id) throws Exception{
        alojamientoService.obtenerPorId(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, alojamientoService.obtenerPorId(id)));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<AlojamientoDTO>>> listarTodos(){
        List<AlojamientoDTO> list = new ArrayList<>(alojamientoService.listarTodos());
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }

    @GetMapping("/buscarPorCiudad")
    public ResponseEntity<ResponseDTO<List<AlojamientoDTO>>> buscarPorCiudad(@RequestParam String ciudad) throws Exception {
        List<AlojamientoDTO> list = new ArrayList<>(alojamientoService.buscarPorCiudad(ciudad));
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }

    @GetMapping("/buscarPorFechas")
    public ResponseEntity<ResponseDTO<List<AlojamientoDTO>>> buscarPorFechas(@RequestParam LocalDateTime fechaInicio, @RequestParam LocalDateTime fechaFin) throws Exception{
        List<AlojamientoDTO> list = new ArrayList<>(alojamientoService.buscarPorFechas(fechaInicio,fechaFin));
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }

    @GetMapping("/buscarPorPrecio")
    public ResponseEntity<ResponseDTO<List<AlojamientoDTO>>> buscarPorPrecio(@RequestParam Double precioMin, @RequestParam Double precioMax) throws Exception{
        List<AlojamientoDTO> list = new ArrayList<>(alojamientoService.buscarPorPrecio(precioMin,precioMax));
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }
    @GetMapping("/buscarPorServicios")
    public ResponseEntity<ResponseDTO<List<AlojamientoDTO>>> buscarPorServicios(@RequestParam List<String> servicios) throws Exception{
        List<AlojamientoDTO> list = new ArrayList<>(alojamientoService.buscarPorServicios(servicios));
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }

    @GetMapping("/listarPorAnfitrion/{idAnfitrion}")
    public ResponseEntity<ResponseDTO<List<AlojamientoDTO>>> listarPorAnfitrion(@PathVariable Long idAnfitrion) throws Exception {
        List<AlojamientoDTO> list = new ArrayList<>(alojamientoService.listarPorAnfitrion(idAnfitrion));
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }
}