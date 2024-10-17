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
	public class ServicioAlumno implements IFServicioAlumno {

		@Autowired
			RepositorioUsuario repoUsuarios;
	
	
	//-------------------------------------------------------------------------------------READ
		
	//****************************************************************

		  @Override
		    public Usuario obtenerProfesorPorLetra(String letraClase) {
			  
		        return repoUsuarios.findByLetraClaseAndRolUsuarioAlumno(letraClase, "profesor");
		    }

	//****************************************************************
		public Usuario findByMailUsuario(String emailUsuario) {
			return repoUsuarios.findByMailUsuario(emailUsuario);
		}


		
	
		

		

}///// FIN CLASE ServicioAlumno

