package es.daw.proyectoDAW.controladores;

import es.daw.proyectoDAW.modelo.Mundo;
import es.daw.proyectoDAW.modelo.Partida;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.servicio.ServicioAlumno;
import es.daw.proyectoDAW.servicio.ServicioMundo;
import es.daw.proyectoDAW.servicio.ServicioPartida;
import es.daw.proyectoDAW.servicio.ServicioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/partidas")
public class ControladorPartida {

    @Autowired
    private ServicioPartida serviPartida;
    
    @Autowired
    private ServicioMundo serviMundo;
    
    @Autowired
    private ServicioAlumno serviAlumno;
//*****************************
    @PostMapping("/iniciarPartida")
    public ResponseEntity<Partida> iniciarPartida(@RequestBody Map<String, String> request) {
        String emailUsuario = request.get("emailUsuario");
        System.out.println("Email del usuario: " + emailUsuario);  // Verifica si el parámetro llega correctamente


    	 Usuario usuario = serviAlumno.findByMailUsuario(emailUsuario);
    	    if (usuario == null) {
    	        return ResponseEntity.badRequest().body(null);
    	    }
    	    
    	    // Obtener el primer mundo activado
    	    Mundo mundo = serviMundo.obtenerPrimerMundoDisponible();
    	    if (mundo == null) {
    	        return ResponseEntity.badRequest().body(null); 
    	    }
    	    
    	    Long mundoId = mundo.getIdMundo();  // Obtener el id del mundo activado
    	    if (mundoId == null) {
    	        return ResponseEntity.badRequest().body(null);  
    	    }
        
    	    // Verificar si el mundo está activado
    	    if (!mundo.getActivadoMundo()) {
    	        return ResponseEntity.badRequest().body(null);  // Si el mundo no está activado
    	    }
    	    
        // Iniciar la partida asociada al usuario y mundo
        Partida partida = serviPartida.iniciarPartida(emailUsuario, mundoId);
        if (partida == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(partida);
    }
  //*****************************
    
    @PostMapping("/iniciar")
    public ResponseEntity<Long> iniciarNuevaPartida(@RequestParam String emailUsuario) {
        // Buscar el usuario por email
        Usuario usuario = serviAlumno.findByMailUsuario(emailUsuario);

        if (usuario == null) {
            // Si el usuario no existe, devolver un error
            return ResponseEntity.badRequest().body(null);
        }

        // Buscar el primer mundo disponible (activadoMundo = false)
        Mundo mundoDisponible = serviMundo.obtenerPrimerMundoDisponible();
        if (mundoDisponible == null) {
            return ResponseEntity.badRequest().body(null); // No hay mundos disponibles
        }

        // Crear la partida con el usuario y el mundo encontrado
        Partida nuevaPartida = serviPartida.crearNuevoJuego(usuario, mundoDisponible);
        if (nuevaPartida == null) {
            return ResponseEntity.badRequest().body(null); // Error al crear la partida
        }

        // Retornar el ID del mundo asociado a la partida
        return ResponseEntity.ok(mundoDisponible.getIdMundo());
    }
    
   //*****************************

    @GetMapping("/{partidaId}/verificar")
    public ResponseEntity<String> verificarEstadoPartidaYEstadoMundo(@PathVariable Long partidaId) {

        // Obtenemos la partida por el ID
        Partida partida = serviPartida.obtenerPartida(partidaId);
        if (partida == null) {
            return ResponseEntity.badRequest().body("La partida no existe.");
        }

        // Obtenemos el objeto Mundo asociado a la partida
        Mundo mundo = partida.getMundoActual(); // Ahora obtenemos el objeto Mundo directamente

        if (mundo == null || !mundo.getActivadoMundo()) {
            return ResponseEntity.badRequest().body("El mundo asociado no está activo.");
        }

        // Si la partida y el mundo están activos, respondemos con un mensaje de éxito
        return ResponseEntity.ok("La partida está activa y el mundo asociado también.");
    }


  //*****************************

    @GetMapping("/{partidaId}/estado")
    public ResponseEntity<String> obtenerEstadoPartida(@PathVariable Long partidaId) {
        // Primero intenta obtener la partida por el ID
        Partida partida = serviPartida.obtenerPartida(partidaId);
        
        // Si no existe, crear una nueva partida
        if (partida == null) {
            partida = serviPartida.iniciarPartidaPorId(partidaId); // Método que crea la partida si no existe
            if (partida == null) {
                return ResponseEntity.badRequest().body("No se pudo crear la partida.");
            }
        }

        // Una vez que la partida existe, podemos verificar su estado
        boolean activa = serviPartida.isPartidaActiva(partidaId);
        if (activa) {
            return ResponseEntity.ok("La partida está activa");
        } else {
            return ResponseEntity.ok("La partida ha finalizado o está en pausa");
        }
    }
    //*****************************

  /*  @GetMapping("/partidas/{partidaId}/tiempo-limite")
    public ResponseEntity<String> obtenerTiempoLimite(@PathVariable Long partidaId) {
        Partida partida = serviPartida.obtenerPartida(partidaId);
        LocalDateTime tiempoLimite = partida.getFechaInicio().plusMinutes(partida.getDuracionPartida());
        return ResponseEntity.ok("Tiempo límite: " + tiempoLimite);
    }*/
}
