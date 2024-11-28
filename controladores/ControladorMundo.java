package es.daw.proyectoDAW.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import es.daw.proyectoDAW.modelo.Mundo;
import es.daw.proyectoDAW.modelo.Partida;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.servicio.ServicioAlumno;
import es.daw.proyectoDAW.servicio.ServicioMundo;
import es.daw.proyectoDAW.servicio.ServicioPartida;
import es.daw.proyectoDAW.servicio.ServicioUsuario;
@RestController 
public class ControladorMundo {

	@Autowired
	private ServicioMundo serviMundo;
	
	@Autowired
	private ServicioUsuario serviUsuario;
	
	@Autowired
	private ServicioPartida serviPartida;

//----------------------------------------------

	// Consultar el estado de un mundo
    @GetMapping("/{id}")
    public Mundo getMundo(@PathVariable Long id) {
        return serviMundo.obtenerMundo(id);
    }
//----------------------------------------------
    
    // Activar un mundo
    @PostMapping("/activar/{id}")
    public Mundo activarMundo(@PathVariable Long id) {
        return serviMundo.activarMundo(id);
    }

//----------------------------------------------

	@PostMapping("/{mundoId}/desactivar")
	public ResponseEntity<String> desactivarMundo(@PathVariable Long mundoId) {
		Mundo mundo = serviMundo.desactivarMundo(mundoId);
		return mundo != null ? ResponseEntity.ok("Mundo desactivado")
				: ResponseEntity.badRequest().body("Error al desactivar el mundo");
	}

	//----------------------------------------------
	
	@PostMapping("/verificar_partida_activa")
	public ResponseEntity<String> verificarPartidaActiva(@RequestParam Long usuarioId) {
	    Partida partida = serviPartida.obtenerPartidaActivaPorUsuario(usuarioId);
	    if (partida != null) {
	        return ResponseEntity.badRequest().body("Ya tienes una partida activa.");
	    }
	    return ResponseEntity.ok("No hay partidas activas.");
	}

	//----------------------------------------------

	@GetMapping("/{mundoId}/estado")
	public ResponseEntity<String> obtenerEstadoMundo1(@PathVariable Long mundoId) {
	    Mundo mundo = serviMundo.verificarYDesactivarMundo(mundoId);
	    if (mundo == null) {
	        return ResponseEntity.badRequest().body("Mundo no encontrado.");
	    }
	    return mundo.getActivadoMundo()
	            ? ResponseEntity.ok("El mundo está activo.")
	            : ResponseEntity.ok("El mundo ha expirado.");
	}
	//----------------------------------------------

	@GetMapping("/api/mundo/actual")
	public ResponseEntity<Mundo> obtenerMundoActualPorEmail(@RequestParam String emailUsuario) {
	   
		Usuario usuario = serviUsuario.findByMailUsuario(emailUsuario);
	 
	    if (usuario == null) {
	        return ResponseEntity.badRequest().body(null);
	    }
	    
	    Mundo mundoActual = serviMundo.obtenerMundo(usuario.getIdUsuario());
	    if (mundoActual == null) {
	        return ResponseEntity.badRequest().body(null);
	    }
	    
	    return ResponseEntity.ok(mundoActual);
	}


}// -----------FIN CONTROLADOR ControladorMundo
