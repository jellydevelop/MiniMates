package es.daw.proyectoDAW.servicio;

import java.util.List;

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

		
		 public String obtenerRolUsuario(String nombreUsuario) {
			 
		        Usuario usuario = repoUsuarios.findByNombreUsuario(nombreUsuario);
		        
		        if (usuario != null) {
		        	
		            return usuario.getRolUsuario();
		            
		        } else {
		            return null;
		        }
		    } 
		
	
	//----------------------------------------------------------CREATE
	//----------------------------------------------------------UPDATE
	//----------------------------------------------------------DELETE

}///// FIN CLASE ServicioUsuario

