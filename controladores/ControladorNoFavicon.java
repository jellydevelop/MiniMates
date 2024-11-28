package es.daw.proyectoDAW.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorNoFavicon {

    @GetMapping("/favicon.ico")
    public void favicon() {
        // Este método no hace nada, pero evita errores.
    }
}
