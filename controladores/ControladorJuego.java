package es.daw.proyectoDAW.controladores;

import es.daw.proyectoDAW.modelo.Partida;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.servicio.ServicioPartida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorJuego {

    @Autowired
    private ServicioPartida servicioPartida;

    // Método que se llama cuando el usuario accede a la acción de jugar
    @GetMapping("/jugar")
    public String jugar(Usuario usuario) {
        // Iniciar una nueva partida
        Partida nuevaPartida = servicioPartida.iniciarPartidaPorId(usuario.getIdUsuario());

        // Redirigir a la vista del juego o cualquier otra acción que necesites
        return "redirect:/partida/" + nuevaPartida.getIdPartida();
    }
}
