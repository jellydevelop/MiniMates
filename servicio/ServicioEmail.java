package es.daw.proyectoDAW.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import es.daw.proyectoDAW.errores.AlumnoNoEncontradoException;
import es.daw.proyectoDAW.errores.ClaseNoEncontradaException;
import es.daw.proyectoDAW.errores.ProfesorNoEncontradoException;
import es.daw.proyectoDAW.modelo.Clase;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.repositorio.RepositorioClase;
import es.daw.proyectoDAW.repositorio.RepositorioUsuario;

@Service
public class ServicioEmail implements IFServicioEmail {

	@Autowired
	private JavaMailSender mailSender;

	// enlazamos el repositorio de Usuario
	@Autowired
	private RepositorioUsuario repoUsuario;

	// enlazamos el repositorio de Usuario
	@Autowired
    private RepositorioClase repoClase;


	// --------------------------------------------

	@Override
	public void sendMailToProfessor(String emailAlumno, String nombreTutor, String cuerpoMensaje)
			throws ProfesorNoEncontradoException, AlumnoNoEncontradoException, ClaseNoEncontradaException {

		// Buscar al alumno usando el email
		Usuario alumno = repoUsuario.findByMailUsuario(emailAlumno);

		if (alumno == null || !alumno.esAlumno()) {
			throw new AlumnoNoEncontradoException("Alumno no encontrado o no eres un alumno.");
		}

		//obtener la clase a la que el alumno pertenece
		Clase clase = alumno.getClase();
        if (clase == null) {
            throw new ClaseNoEncontradaException("El alumno no está asignado a ninguna clase.");
        }
		
		// Obtener mail del profesor basado en la letra de la clase
        Usuario profesor = repoClase.findProfesorByLetraClase(clase.getLetraClase());
        if (profesor == null ) {
            throw new ProfesorNoEncontradoException("No se encontró un profesor asignado a la clase: " + clase.getLetraClase());
        }


		// Preparar el mensaje de correo
		SimpleMailMessage mensaje = new SimpleMailMessage();
		mensaje.setTo(profesor.getMailUsuario());
		mensaje.setSubject("CONSULTA DE " + nombreTutor);
		mensaje.setText("Mensaje:\n" + cuerpoMensaje + "\n\nEnviado por: " + alumno.getNombreUsuario());
		mensaje.setFrom(alumno.getMailUsuario()); // El correo que se utiliza para el envío

		// Enviar el mensaje
		mailSender.send(mensaje); // Envía el correo
	}


	
}