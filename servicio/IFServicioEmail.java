package es.daw.proyectoDAW.servicio;

import es.daw.proyectoDAW.errores.AlumnoNoEncontradoException;
import es.daw.proyectoDAW.errores.ClaseNoEncontradaException;
import es.daw.proyectoDAW.errores.ProfesorNoEncontradoException;
///*****************************************************************************IMPORTS

public interface IFServicioEmail {
	
	
	public void sendMailToProfessor(String emailAlumno, String nombreTutor, String cuerpoMensaje) throws ProfesorNoEncontradoException, AlumnoNoEncontradoException, ClaseNoEncontradaException;

}
