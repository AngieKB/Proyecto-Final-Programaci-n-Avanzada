package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.DTO.*;
import co.edu.uniquindio.Application.Services.ReservaService;
import co.edu.uniquindio.Application.Services.impl.ReservaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reserva")

public class ReservaController {
    private final ReservaServiceImpl reservaService;


    public ResponseEntity<ResponseDTO<String>> create(@Valid @RequestBody ReservaDTO reservaDTO) throws Exception {
        reservaService.guardar(reservaDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "La reserva ha sido registrada"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> edit(@PathVariable Long id, @Valid @RequestBody ReservaDTO reservaDTO, @Valid@RequestBody UbicacionDTO ubicacionDTO) throws Exception{
        reservaService.editarReserva(id, reservaDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> delete(@PathVariable Long id) throws Exception{
        reservaService.cancelarReserva(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UsuarioDTO>> get(@PathVariable Long id) throws Exception{
        reservaService.obtenerPorId(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }
}
