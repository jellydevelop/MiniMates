/*package es.daw.proyectoDAW.servicio;

import org.springframework.stereotype.Service;
import es.daw.proyectoDAW.errores.AlumnoNoEncontradoException;
import es.daw.proyectoDAW.errores.ProfesorNoEncontradoException;
import es.daw.proyectoDAW.modelo.Alumno;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.seguridad.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class ServicioEmail {

	// Enlazamos con la librería JAVAMAILSENDER
	@Autowired
	private JavaMailSender mailSender;

	// Enlazamos con repositorio
	@Autowired
	private ServicioAlumno repoUsuario;

	// Enlazamos con JWT seguridad
	@Autowired
	private JWTUtil tokenJWT;
	
	
	//--------------------------------------------

	// Creamos el método que manejará los mensajes
	public void sendMailToProfessor(String token, String nombreTutor, String cuerpoMensaje) 
	        throws ProfesorNoEncontradoException, AlumnoNoEncontradoException {
	    
	    // Obtenemos el DESDE (mail del alumno logueado desde el token y lo buscamos en la BDD)
	    String emailAlumno = tokenJWT.obtenerEmailDesdeToken(token);
	    Usuario usuarioAlumno = repoUsuario.findByMailUsuario(emailAlumno);

	    // Validamos que el usuario existe
	    if (usuarioAlumno == null) {
	        throw new AlumnoNoEncontradoException("Alumno no encontrado con el email proporcionado.");
	    }

	    // Comprobamos si el usuario es un Alumno
	    if (usuarioAlumno instanceof Alumno) {
	        Alumno alumno = (Alumno) usuarioAlumno; // Casteo seguro

	        // Obtener la letra de la clase a la que pertenece el alumno
	        String letraClase = alumno.getClase().getLetraClase(); // Acceso directo a la letra de la clase

	        // Ahora puedes continuar con la lógica para obtener el profesor
	        Usuario paraProfesor = repoUsuario.obtenerProfesorPorLetra(letraClase);

	        // Validamos que el profesor existe
	        if (paraProfesor == null) {
	            throw new ProfesorNoEncontradoException("Profesor no encontrado para la letra de clase: " + letraClase);
	        }

	        // Creamos asunto y cuerpo del mensaje
	        String asunto = "CONSULTA DE " + nombreTutor;
	        String texto = "Mensaje de:\n\n " + cuerpoMensaje + "\n\nEnviado por: " + alumno.getNombreUsuario();

	        // Creamos y enviamos el mensaje
	        SimpleMailMessage mensaje = new SimpleMailMessage();
	        mensaje.setTo(paraProfesor.getMailUsuario());
	        mensaje.setSubject(asunto);
	        mensaje.setText(texto);
	        mensaje.setFrom(alumno.getMailUsuario());

	        // Mandamos el mensaje con JavaMail
	        mailSender.send(mensaje);
	    } else {
	        throw new AlumnoNoEncontradoException("El usuario no es un alumno.");
	    }
	    
	}
}
	*/