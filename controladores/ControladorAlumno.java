package es.daw.proyectoDAW.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.daw.proyectoDAW.modelo.Clase;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.repositorio.RepositorioUsuario;
import es.daw.proyectoDAW.servicio.ServicioAlumno;
//import es.daw.proyectoDAW.servicio.ServicioEmail;
import es.daw.proyectoDAW.servicio.ServicioProfesor;

////***************************************************IMPORTS

@CrossOrigin(origins = "http://localhost:8080")

@RestController
@RequestMapping("/alumno")

public class ControladorAlumno {
	
	   @Autowired
	    private RepositorioUsuario repoUsuario;
	////ENLAZAMOS  SERVICIO DE CORREO
//	@Autowired ServicioEmail serviMail;
	   
	   @Autowired
	    private ServicioAlumno serviAlum;
	
	
			
			//----------------------------------------------------------------------------CREATE
			
			/* @PostMapping("/enviarMail")
			    public ResponseEntity<String> enviarCorreo(
			            @RequestHeader("Authorization") String token,  // Token JWT en los headers
			            @RequestParam("nombreTutor") String nombreTutor, // Nombre del tutor del formulario
			            @RequestParam("cuerpoMensaje") String cuerpoMensaje) { // Mensaje del formulario

			        try {
			        	
			            // Llamamos al servicio para enviar el correo
			        	serviMail.sendMailToProfessor(token, nombreTutor, cuerpoMensaje);
			            	return ResponseEntity.ok("Correo enviado con éxito");
			            	
			        } catch (Exception e) {
			        	
			            return ResponseEntity.status(500).body("Error al enviar el correo: " + e.getMessage());
			        }
			    }*/
			//----------------------------------------------------------------------------READ
			
			 @GetMapping("/obtenerLetraClase/{emailAlumno}")
			    public ResponseEntity<String> obtenerLetraClase(@PathVariable String emailAlumno) {
			        // Buscar al alumno usando el email
			        Usuario alumno = repoUsuario.findByMailUsuario(emailAlumno);

			        // Verificar que el alumno existe
			        if (alumno == null || !alumno.esAlumno()) {
			            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado.");
			        }

			        // Obtener la clase del alumno
			        Clase clase = alumno.getClase();
			        if (clase == null) {
			            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El alumno no está asignado a ninguna clase.");
			        }

			        // Devolver la letra de la clase
			        return ResponseEntity.ok(clase.getLetraClase());
			    }
		 ////---------------------------------------
			 
			  @GetMapping("/obtenerIniciales/{emailAlumno}")
			    public ResponseEntity<String> obtenerIniciales(@PathVariable String emailAlumno) {
				  
			        String iniciales = serviAlum.obtenerInicialesPorEmail(emailAlumno);
			        
			        if (iniciales != null) {
			            return ResponseEntity.ok(iniciales);
			        } else {
			            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Iniciales no encontradas");
			        }
			        }
			//----------------------------------------------------------------------------UPDATE
			//----------------------------------------------------------------------------DELETE



			  

}
