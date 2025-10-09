package co.edu.uniquindio.Application.Controllers;


import co.edu.uniquindio.Application.DTO.*;
import co.edu.uniquindio.Application.DTO.Alojamiento.AlojamientoDTO;
import co.edu.uniquindio.Application.DTO.Alojamiento.CrearAlojamientoDTO;
import co.edu.uniquindio.Application.DTO.Alojamiento.UbicacionDTO;
import co.edu.uniquindio.Application.Services.AlojamientoService;
import co.edu.uniquindio.Application.Services.impl.AlojamientoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alojamiento")
public class AlojamientoController {

    private final AlojamientoService alojamientoService;

    @PreAuthorize("hasRole('ANFITRION')")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ResponseDTO<String>> crear(@Valid @ModelAttribute CrearAlojamientoDTO alojamientoDTO) throws Exception {
        alojamientoService.guardar(alojamientoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El alojamiento ha sido registrado"));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> editar(@PathVariable Long id, @Valid @RequestBody AlojamientoDTO alojamientoDTO, @Valid@RequestBody UbicacionDTO ubicacionDTO) throws Exception{
        alojamientoService.editarAlojamiento(id, alojamientoDTO, ubicacionDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El alojamiento ha sido actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> eliminar(@PathVariable Long id) throws Exception{
        alojamientoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDTO<>(false, "El usuario ha sido eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<AlojamientoDTO>> obtenerPorId(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, alojamientoService.obtenerPorId(id)));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<AlojamientoDTO>>> listarTodos() throws Exception {
        List<AlojamientoDTO> list = new ArrayList<>(alojamientoService.listarTodos());
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }

    @GetMapping("/buscar/ciudad")
    public ResponseEntity<ResponseDTO<List<AlojamientoDTO>>> buscarPorCiudad(@RequestParam String ciudad) throws Exception {
        List<AlojamientoDTO> list = new ArrayList<>(alojamientoService.buscarPorCiudad(ciudad));
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }

    @GetMapping("/buscar/fechas")
    public ResponseEntity<ResponseDTO<List<AlojamientoDTO>>> buscarPorFechas(@RequestParam LocalDateTime fechaInicio, @RequestParam LocalDateTime fechaFin) throws Exception{
        List<AlojamientoDTO> list = new ArrayList<>(alojamientoService.buscarPorFechas(fechaInicio,fechaFin));
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }

    @GetMapping("/buscar/precio")
    public ResponseEntity<ResponseDTO<List<AlojamientoDTO>>> buscarPorPrecio(@RequestParam Double precioMin, @RequestParam Double precioMax) throws Exception{
        List<AlojamientoDTO> list = new ArrayList<>(alojamientoService.buscarPorPrecio(precioMin,precioMax));
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }
    @GetMapping("/buscar/" +"servicios")
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