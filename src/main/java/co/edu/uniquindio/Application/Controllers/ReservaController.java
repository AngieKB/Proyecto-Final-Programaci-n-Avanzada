package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.DTO.*;
import co.edu.uniquindio.Application.DTO.Alojamiento.UbicacionDTO;
import co.edu.uniquindio.Application.DTO.Reserva.*;
import co.edu.uniquindio.Application.DTO.Usuario.UsuarioDTO;
import co.edu.uniquindio.Application.Services.impl.ReservaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reserva")

public class ReservaController {
    private final ReservaServiceImpl reservaService;

    @PreAuthorize("hasRole('HUESPED')")
    @PostMapping("/crear")
    public ResponseEntity<ResponseDTO<String>> create(@Valid @RequestBody RealizarReservaDTO realizarReservaDTO) throws Exception {
        reservaService.guardar(realizarReservaDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "La reserva ha sido registrada"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> edit(@PathVariable Long id, @Valid @RequestBody EditarReservaDTO reservaDTO, @Valid@RequestBody UbicacionDTO ubicacionDTO) throws Exception{
        reservaService.editarReserva(id, reservaDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> delete(@PathVariable Long id) throws Exception{
        reservaService.cancelarReserva(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, "La reserva ha sido cancelada"));
    }

    @PutMapping("/actualizar-completadas")
    public ResponseEntity<ResponseDTO<String>> actualizarReservasCompletadas() {
        reservaService.actualizarReservasCompletadas();
        return ResponseEntity.ok(
                new ResponseDTO<>(false, "Reservas completadas actualizadas correctamente")
        );
    }


    @GetMapping("/mis-reservas/{usuarioId}")
    public ResponseEntity<ResponseDTO<List<ReservaUsuarioDTO>>> obtenerMisReservas(@PathVariable Long usuarioId) {
        List<ReservaUsuarioDTO> reservas = reservaService.obtenerReservasPorIdHuesped(usuarioId);
        return ResponseEntity.ok(new ResponseDTO<>(false, reservas));
    }
    @GetMapping("/mis-reservas-aloja/{alojamientoId}")
    public ResponseEntity<ResponseDTO<List<ReservaAlojamientoDTO>>> obtenerMisReservasPorAlojamiento(@PathVariable Long alojamientoId) {
        List<ReservaAlojamientoDTO> reservas = reservaService.obtenerReservasPorIdAlojamiento(alojamientoId);
        return ResponseEntity.ok(new ResponseDTO<>(false, reservas));
    }

}
