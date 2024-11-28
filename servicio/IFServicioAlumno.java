package es.daw.proyectoDAW.servicio;

import es.daw.proyectoDAW.modelo.Usuario;

public interface IFServicioAlumno {
	

	//------------------------------------------------------------------------------READ
	
	// public Usuario obtenerProfesorPorLetra(String letraClase);

		public Usuario findByMailUsuario(String emailUsuario) ;

	 

}