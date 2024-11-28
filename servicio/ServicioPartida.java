package es.daw.proyectoDAW.servicio;

import es.daw.proyectoDAW.modelo.Mundo;
import es.daw.proyectoDAW.modelo.Partida;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.repositorio.RepositorioMundo;
import es.daw.proyectoDAW.repositorio.RepositorioPartida;
import es.daw.proyectoDAW.repositorio.RepositorioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ServicioPartida {

	@Autowired
	private RepositorioPartida repoPartida;

	@Autowired
	private RepositorioMundo repoMundo;

	@Autowired
	private RepositorioUsuario repoUser;

//**************************
	public Partida iniciarPartida(String emailUsuario) {

		// buscamos al alumno
		Usuario usuario = repoUser.findByMailUsuario(emailUsuario);

		   if (usuario == null) {
		        throw new IllegalArgumentException("Usuario no encontrado");
		    }
		   
		// Crear una nueva partida para el usuario
		Partida nuevaPartida = new Partida();
		nuevaPartida.setUsuario(usuario);
		nuevaPartida.setFechaInicio(LocalDateTime.now());
		nuevaPartida.setInicioPartida(LocalDateTime.now());
		nuevaPartida.setDuracionPartida(15);
		nuevaPartida.setEstado("ACTIVA");

		// Buscar el primer Mundo que no esté desactivado
		Mundo mundoDisponible = repoMundo.findFirstByActivadoMundoTrue();

		if (mundoDisponible == null) {
	        throw new IllegalStateException("No hay mundos disponibles");
	    }
		
		// Establecer el mundoActual en la partida
		nuevaPartida.setMundoActual(mundoDisponible);

		// Guardar la partida en la base de datos
		return repoPartida.save(nuevaPartida);
	}

//**************************
	public boolean isPartidaActiva(Long partidaId) {
		Optional<Partida> partida = repoPartida.findById(partidaId);
		return partida.map(Partida::isPartidaActiva).orElse(false);
	}

//**************************
	public void pasarAlSiguienteMundo(Partida partida) {

		// Validamos que han pasado 7 días desde el inicio del mundo actual
		if (!puedePasarAlSiguienteMundo(partida)) {
			throw new IllegalStateException("No han pasado los 7 días desde el inicio del mundo actual.");
		}

		// Obtener el siguiente mundo disponible para la partida
		Mundo siguienteMundo = repoMundo.findFirstByActivadoMundoFalse();

		if (siguienteMundo == null) {
			throw new IllegalStateException("No hay más mundos disponibles para esta partida.");
		}

		// Actualizar el mundoActual de la partida
		partida.setMundoActual(siguienteMundo);

		// Guardar la partida con el nuevo mundo
		repoPartida.save(partida);
	}
	// **************************
	
	 public Partida crearNuevoJuego(Usuario usuario, Mundo mundo) {
	        // Crear la instancia de la nueva partida
	        Partida partida = new Partida();
	        partida.setUsuario(usuario);
	        partida.setMundoActual(mundo);
	        partida.setFechaInicio(LocalDateTime.now());
	        partida.setInicioPartida(LocalDateTime.now());
	        partida.setDuracionPartida(15); // Duración predeterminada
	        partida.setEstado("En curso");

	        // Guardar la partida en la base de datos
	        return repoPartida.save(partida);
	    }
		// **************************

	public Partida obtenerPartida(Long partidaId) {
		return  repoPartida.findByIdPartida(partidaId);
	}
//**************************

	public boolean puedePasarAlSiguienteMundo(Partida partida) {
		 // Obtienes el mundo actual de la partida
	    Mundo mundoActual = partida.getMundoActual();

	    // Verifica si el mundo actual es nulo
	    if (mundoActual == null) {
	        throw new IllegalArgumentException("La partida no tiene un mundo asociado.");
	    }

	    // Si tienes un campo `id` en Mundo, obtenemos el id y buscamos el mundo en la base de datos
	    Long mundoId = mundoActual.getIdMundo(); 

	    // Buscamos el mundo en el repositorio usando el ID
	    Mundo mundo = repoMundo.findById(mundoId).orElse(null);

	    // Si el mundo no existe, lanzamos una excepción
	    if (mundo == null) {
	        throw new IllegalArgumentException("El mundo actual no existe.");
	    }

	    // Compara si han pasado más de 7 días desde la fecha de inicio del mundo
	    LocalDateTime fechaInicioMundoActual = mundo.getFechaInicioMundo();
	    return fechaInicioMundoActual.plusDays(7).isBefore(LocalDateTime.now());
	}

// **************************

	public Partida iniciarPartida(String emailUsuario, Long mundoId) {
		
		//bucamos al usuario
	    Usuario usuario = repoUser.findByMailUsuario(emailUsuario);
	    if (usuario == null) {
	        throw new IllegalArgumentException("Usuario no encontrado");
	    }
	    //buscar mundo
	    Mundo mundo = repoMundo.findById(mundoId).orElse(null);
	    if (mundo == null || !mundo.getActivadoMundo()) {
	        throw new IllegalArgumentException("Mundo no válido o no activado");
	    }
	    
	 // Actualizar el estado del mundo
	    mundo.setEstado("true"); 
	    repoMundo.save(mundo); 

	    // Crear nueva partida
	    Partida nuevaPartida = new Partida();
	    nuevaPartida.setUsuario(usuario);
	    nuevaPartida.setMundoActual(mundo);
	    nuevaPartida.setFechaInicio(LocalDateTime.now());
	    nuevaPartida.setDuracionPartida(30); // Ejemplo de duración en minutos
	    nuevaPartida.setEstado("activo");
	    
	    
	    // Guardar partida
	    return repoPartida.save(nuevaPartida);
	}

// **************************

	public Partida obtenerPartidaActivaPorUsuario(Long usuarioId) {
	    return repoPartida.findFirstByUsuario_IdUsuarioAndEstado(usuarioId, "ACTIVA");
	}
	// **************************

	public Partida iniciarPartidaPorId(Long partidaId) {
		return repoPartida.findByIdPartida(partidaId);
	}

	public Partida crearNuevaPartida(Usuario usuario, Mundo mundoDisponible) {
		// TODO Auto-generated method stub
		return null;
	}

}
