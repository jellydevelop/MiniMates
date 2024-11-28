package es.daw.proyectoDAW.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.daw.proyectoDAW.modelo.Clase;
import es.daw.proyectoDAW.modelo.Partida;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.repositorio.RepositorioUsuario;


@Service
public class ServicioUsuario implements IFServicioUsuario{

    @Autowired
    private RepositorioUsuario repoUsuarios;
    
    //---------------------------------------------------------------------CREATE
  /*  ///*******para asignar un usuario a una clase ->>
    public void asignarUsuarioAClasePorLetra(Long usuarioId, String letraClase) throws Exception {

        // Buscar la clase por letraClase usando el método personalizado en repoUsuarios
        Clase clase = repoUsuarios.findClaseByLetraClase(letraClase)
                .orElseThrow(() -> new Exception("Clase con letra " + letraClase + " no encontrada"));

        // Buscar el usuario por ID usando repoUsuarios
        Usuario usuario = repoUsuarios.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario con ID " + usuarioId + " no encontrado"));

        // Asigna la clase al usuario y guarda el usuario en el repositorio
        usuario.setClase(clase);
        repoUsuarios.save(usuario);
    }
*/
    //---------------------------------------------------------------------READ
    
    ///*******para buscar  email + password ->> para verificar login
    public Usuario findByMailAndPass(String mail, String pass) {
    

        return repoUsuarios.findByMailAndPass(mail, pass);
    }
    ///*******para buscar solo email ->> para mandar correos
	public Usuario findByMailUsuario(String emailUsuario) {
		return repoUsuarios.findByMailUsuario(emailUsuario);
	}
	
   
    //---------------------------------------------------------------------READ

	public void eliminarUsuario(Usuario usuario) {
	    // Eliminar las partidas del usuario
	    List<Partida> partidas = usuario.getPartidas();
	    for (Partida partida : partidas) {
	        partida.setUsuario(null); // Desasociar la partida del usuario
	    }

	    // Eliminar el usuario y sus partidas (si se usa orphanRemoval, las partidas se eliminan automáticamente)
	    usuario.getPartidas().clear();  // Elimina las partidas del usuario (si orphanRemoval está activado)
	    usuario.setClase(null);  // Asegúrate de desasociar la clase si no deseas eliminarla

	    // Eliminar el usuario
	    repoUsuarios.delete(usuario);
	}

	
	

}