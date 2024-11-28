package es.daw.proyectoDAW.servicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.daw.proyectoDAW.modelo.Mundo;
import es.daw.proyectoDAW.repositorio.RepositorioMundo;

////****************************************************************************IMPORTS
@Service
public class ServicioMundo implements IFServicioMundo {

	@Autowired
	RepositorioMundo repoMundo;

	// -------------------------------------------------------------------------------------READ

	public List<Mundo> obtenerMundos() {

		///// CASTEAMOS A UN ITERABLE
		return repoMundo.findAll();

	}

//******************************	
	public Mundo obtenerMundo(Long id) {
		return repoMundo.findById(id).orElse(null);
	}
	// ******************************

	public boolean isMundoActivo(Long mundoId) {
		Mundo mundo = obtenerMundo(mundoId);
		return mundo != null && mundo.getActivadoMundo();
	}
	// ******************************

	public Mundo desactivarMundo(Long mundoId) {
		Mundo mundo = obtenerMundo(mundoId);
		if (mundo != null) {
			mundo.setActivadoMundo(false);
			return repoMundo.save(mundo);
		}
		return null;
	}

	// ******************************
	public Mundo activarMundo(Long id) {
		Mundo mundo = obtenerMundo(id);

		if (!mundo.isActivado()) {
			mundo.setActivadoMundo(true);
			mundo.setFechaInicioMundo(LocalDateTime.now());
			repoMundo.save(mundo);
		}
		return mundo;
	}
	// ******************************
	// ******************************
	// ******************************
	// ******************************

	/// ----------------------------------------------------

	public List<Mundo> obtenerAllPantallasOneMundo(Long idMundo) {

		return repoMundo.findAll();

	}
	/// ----------------------------------------------------

	// para desactivar automaticamente el mundo
	public Mundo verificarYDesactivarMundo(Long mundoId) {
		Mundo mundo = obtenerMundo(mundoId);
		if (mundo != null && mundo.isExpirado()) {
			mundo.setActivadoMundo(false);
			return repoMundo.save(mundo);
		}
		return mundo;
	}
	// ******************************

	public Mundo obtenerMundoPorId(Long mundoId) {
	    return repoMundo.findById(mundoId).orElse(null);
	}

	public Mundo obtenerPrimerMundoDisponible() {
        return repoMundo.findFirstByActivadoMundoTrue();
	}

	

//-----------------------------------------------------------------------------------------CREATE

//--------------------------------------------------------------------------------------------UPDATE

//--------------------------------------------------------------------------------------------DELETE

}// -----------FIN ServicioMundo
