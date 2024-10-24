package es.daw.proyectoDAW.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.repositorio.RepositorioUsuario;

////****************************************************************************IMPORTS

	@Service
	public class ServicioProfesor implements IFServicioProfesor {

		@Autowired
			RepositorioUsuario repoUsuarios;
	
	
	//-------------------------------------------------------------------------------------READ
		
		public List<Usuario>  obtenerUsuarios(){
			
			/////CASTEAMOS A UN ITERABLE
				return (List<Usuario>) repoUsuarios.findAll();
			
		}
		
		///----------------------------------------------------

		@Override
		public Optional<List<Usuario>> obtenerUsuariosPorRol(String rol) {
			
		    List<Usuario> usuarios = repoUsuarios.findByRolUsuario(rol);
		    
		    
		    if (usuarios.isEmpty()) {
		    	
		        return Optional.empty();
		        
		    } else {
		    	
		        return Optional.of(usuarios);
		    }
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
		
		///----------------------------------------------------
		
		public Usuario findByMailAndPass( String mail, String pass) {
			
		    return repoUsuarios.findByMailAndPass(mail, pass);
			
		}

	
	//-----------------------------------------------------------------------------------------CREATE
		public Usuario aniadeUsuarioAlumno(Usuario alum) {
		    if(alum == null) {
		        throw new IllegalArgumentException("El usuario no puede ser null");
		    }

		    // Guarda el usuario alumno 
		    return repoUsuarios.save(alum);
		}

		
		///----------------------------------------------------
		
		//// fututo metodo añadir un alumno asociado a un profesor
		
		

	//--------------------------------------------------------------------------------------------UPDATE
		public Optional<Usuario> actualizarUsuarioPorMail(String mailUsuario, Usuario alumProf) {
		    // BUSCAMOS EL USUARIO POR SU CORREO ELECTRÓNICO
		    Optional<Usuario> buscaUser = repoUsuarios.findOptionalByMailUsuario(mailUsuario);

		    try {
		    	
		    	//Validamos que no qude vacío este dato
		    	if (alumProf == null) {
		    	    throw new IllegalArgumentException("El objeto Usuario no puede ser nulo.");
		    	}
		    
		    if (buscaUser.isPresent()) {
		        // Atrapamos el usuario
		        Usuario userParaCambiar = buscaUser.get();

		        // Actualizamos solo los campos proporcionados en la solicitud
		        if (alumProf.getNombreUsuario() != null) {
		            userParaCambiar.setNombre(alumProf.getNombreUsuario());
		        }
		        if (alumProf.getMailUsuario() != null) {
		            userParaCambiar.setMail(alumProf.getMailUsuario());
		        }

		        // Guardamos los cambios en el repositorio y devolvemos el usuario modificado
		        return Optional.of(repoUsuarios.save(userParaCambiar));
		    
		    } else {
		        // Si el usuario no existe, devolvemos un Optional vacío
		        return Optional.empty();
		    }
		    
		  } catch (Exception e) {
	        e.printStackTrace();
	        return Optional.empty(); // O manejar la excepción según tu lógica
			

		  }
	}
		///----------------------------------------------------
		
		//// fututo metodo actualizar un alumno asociado a un profesor
		
		
		
	//--------------------------------------------------------------------------------------------DELETE
		
	/*	@Override
		public Optional<Usuario> borrarUsuarioPorId(Long id) {
			
		    // Busca el usuario por el ID
		    Optional<Usuario> userOptional = repoUsuarios.findById(id);
		  
		   
		    if (userOptional.isPresent()) {
		    	
		        // Elimina el usuario de la base de datos
		    	repoUsuarios.delete(userOptional.get());
		        
		        // Devuelve  el usuario eliminado
		        return userOptional;
		        
		    } else {
		    	
		        return null;
		    }
		}
		*/
		///----------------------------------------------------

		@Override
		public Optional<Usuario> borrarUsuarioPorMail(String mailUsuario) {
			
		    // Busca el usuario por el MAIL 
		    Optional<Usuario> user = repoUsuarios.findOptionalByMailUsuario(mailUsuario);
		  
		   
		    if (user.isPresent()) {
		    	
		    	// Si el usuario existe, devuélvelo para su posible uso futuro
		        Usuario usuarioBorrado = user.get();
		    	
		        // Elimina el usuario de la base de datos
		    	repoUsuarios.delete(usuarioBorrado);
		        
		    	// Devuelve el usuario eliminado
		        return Optional.of(usuarioBorrado);
		        
		    } else {
		    	
		         return Optional.empty();
		    }
		}
		

		///----------------------------------------------------
		///----------------------------------------------------
		///----------------------------------------------------

		@Override
		public Usuario actualizarUsuarioPorId(Usuario alumProf) {
			// TODO Auto-generated method stub
			return null;
		}

	

	
		

		

}///// FIN CLASE ServicioUsuario

