package es.daw.proyectoDAW.servicio;

import es.daw.proyectoDAW.errores.AlumnoNoEncontradoException;
import es.daw.proyectoDAW.errores.ProfesorNoEncontradoException;
///*****************************************************************************IMPORTS
public interface IFServicioEmail {
	
	//-------------------------------------------------------------READ
	void sendMailToProfessor(String token, String nombreTutor, String cuerpoMensaje) 
            throws ProfesorNoEncontradoException, AlumnoNoEncontradoException;

}
