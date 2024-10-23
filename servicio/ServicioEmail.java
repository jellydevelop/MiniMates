package es.daw.proyectoDAW.servicio;

import org.springframework.stereotype.Service;

import es.daw.proyectoDAW.errores.AlumnoNoEncontradoException;
import es.daw.proyectoDAW.errores.ProfesorNoEncontradoException;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.seguridad.JWTUtil;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class ServicioEmail {

	
	////Enlazamos con la librería JAVAMAILSENDER
		@Autowired 
			private JavaMailSender mailSender;
	
	////Enlazamos con repositorio
		@Autowired 
			private ServicioAlumno repoUsuario;
		
	////Enlazamos con JWT seguridad
	    @Autowired
	    private JWTUtil tokenJWT;
		
		
			
			
		
	//creamos el metodo que manejará los mensajes
	    public void sendMailToProfessor(String token, String nombreTutor, String cuerpoMensaje) throws ProfesorNoEncontradoException, AlumnoNoEncontradoException { 
			
	  //obtenemos el DESDE (mail del alumno logueado desde el token y lo buscamos en la bdd)
	    	String emailAlumno = tokenJWT.obtenerEmailDesdeToken(token);
	        Usuario alumno = repoUsuario.findByMailUsuario(emailAlumno);
	        
			      
	        // --> 
		        if (alumno == null) {
		            throw new AlumnoNoEncontradoException("Alumno no encontrado con el email proporcionado."); 
		        }
				
				
	// Obtener el TO (la letra de la clase del alumno + profesor de esa clase)
					String letraClase = alumno.getLetraClase();
					
				 // -->
			        Usuario paraProfesor = repoUsuario.obtenerProfesorPorLetra( letraClase);
			        
			        if (paraProfesor == null) {
			            throw new ProfesorNoEncontradoException("Profesor no encontrado para la letra de clase: " + letraClase);  
			        }
			
			        
	// creamos asunto y cuerpo del mensaje
			        String asunto="CONSULTA DE "+nombreTutor;
			        
			        String texto= "Mensaje de:\n\n "
			        			+cuerpoMensaje
			        			+"\n\nEnviado por: " 
			        			+ alumno.getNombreUsuario();
			        
			        
	//creamos y enviamos el mensaje
				SimpleMailMessage mensaje = new SimpleMailMessage();
				
					// --> datos para la creación del mensaje
				
						mensaje.setTo(paraProfesor.getMailUsuario());
						mensaje.setSubject(asunto);
						mensaje.setText(texto);
				        mensaje.setFrom(alumno.getMailUsuario());

					
							//mandamos con JavaMail
								mailSender.send(mensaje);
					
				
		}



		
		
	
}
