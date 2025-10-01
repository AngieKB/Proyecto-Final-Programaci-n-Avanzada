package co.edu.uniquindio.Application.Controllers;

import co.edu.uniquindio.Application.Services.impl.ComentarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comentarios")
public class ComentarioController {
    private final ComentarioServiceImpl comentarioService;
}
