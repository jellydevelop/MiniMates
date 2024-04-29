package es.daw.proyectoDAW.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.repositorio.RepositorioUsuarios;

////****************************************************************************IMPORTS

	@Service
	public class ServicioUsuario implements IFServicioUsuario {

		@Autowired
			RepositorioUsuarios repoUsuarios;
	
	
	//----------------------------------------------------------READ
		
		public List<Usuario>  obtenerUsuarios(){
			
			/////CASTEAMOS A UN ITERABLE
				return (List<Usuario>) repoUsuarios.findAll();
			
		}

		///----------------------------------------------------

		public String obtenerRolUsuario(Long idUsuario) {
			
	        Optional<Usuario> usuarioConsultaRol = repoUsuarios.findById(idUsuario);

	        if (usuarioConsultaRol.isPresent()) {
	        	
	            Usuario usuario = usuarioConsultaRol.get();
	            
	            return usuario.getRolUsuario();
	            
	        } else {
	        	
	        	return "sin rol o  no existe el usuario"; 
	        }
	    }
		
		///----------------------------------------------------

		public Optional<Usuario> verificarExisteUsuario(String correo, String password) {
			
			Usuario usuario = repoUsuarios.findByMailUsuario(correo);
			
		    return Optional.ofNullable(usuario);
		}
		
		///----------------------------------------------------

		public Optional<Usuario> findByMail(String correo) {
			
		    Usuario usuario = repoUsuarios.findByMailUsuario(correo);
		    
		    return Optional.ofNullable(usuario);
		}
		///----------------------------------------------------
		public boolean checkPassword(Usuario usuario, String password) {
			
		    return usuario.getPassUsuario().equals(password);
		}

	


		
			
		
	
		
	
	//----------------------------------------------------------CREATE
	//----------------------------------------------------------UPDATE
	//----------------------------------------------------------DELETE

}///// FIN CLASE ServicioUsuario

