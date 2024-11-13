package es.daw.proyectoDAW.servicio;

import org.springframework.stereotype.Service;
import es.daw.proyectoDAW.errores.AlumnoNoEncontradoException;
import es.daw.proyectoDAW.errores.ProfesorNoEncontradoException;
import es.daw.proyectoDAW.herramientas.JWTUtil;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.repositorio.RepositorioUsuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class ServicioEmail implements IFServicioEmail {
	
    private final JavaMailSender javaMailSender;


	@Autowired
	private JavaMailSender mailSender;

	
	// enlazamos el repositorio de Usuario 
    @Autowired
    private RepositorioUsuario repoUsuario;

	// Enlazamos con JWT seguridad
	@Autowired
	private JWTUtil tokenJWT;
	
	 // Constructor para la inyección de JavaMailSender
		@Autowired
	    public ServicioEmail(JavaMailSender javaMailSender) {
	        this.javaMailSender = javaMailSender;
	    }
	
	
	//--------------------------------------------

	 @Override
	    public void sendMailToProfessor(String token, String nombreTutor, String cuerpoMensaje)
	            throws ProfesorNoEncontradoException, AlumnoNoEncontradoException {

	        // El token ahora es el email del alumno
	        String emailAlumno = token; // Usamos el token como email

	        // Buscar al alumno usando el email
	        Usuario alumno = repoUsuario.findByMailUsuario(emailAlumno);

	        if (alumno == null) {
	            throw new AlumnoNoEncontradoException("Alumno no encontrado.");
	        }

	        // Obtener el profesor basado en la letra de la clase
	        String letraClase = alumno.getClase().getLetraClase();
	        
	        Usuario profesor = repoUsuario.findByLetraClaseAndRolUsuarioProfesor(letraClase);

	        if (profesor == null) {
	            throw new ProfesorNoEncontradoException("Profesor no encontrado para la letra de clase: " + letraClase);
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