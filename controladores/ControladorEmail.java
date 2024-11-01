/*package es.daw.proyectoDAW.controladores;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.daw.proyectoDAW.errores.AlumnoNoEncontradoException;
import es.daw.proyectoDAW.errores.ProfesorNoEncontradoException;
import es.daw.proyectoDAW.modelo.EmailRequest;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.servicio.IFServicioEmail;
import es.daw.proyectoDAW.servicio.ServicioEmail;
import es.daw.proyectoDAW.servicio.ServicioProfesor;



////***************************************************IMPORTS
@RestController
public class ControladorEmail {
	
	@Autowired
    private IFServicioEmail servicioEmail;

	//------------
    @PostMapping("/enviar_email")
    
    public ResponseEntity<String> enviarEmail(@RequestBody EmailRequest emailRequest) {
    	
        try {
        	
        	// Llama al servicio para enviar el email
            servicioEmail.sendMailToProfessor(emailRequest.getToken(),
            		emailRequest.getNombreTutor(),
            		emailRequest.getCuerpoMensaje());
            
            
            return ResponseEntity.ok("Email enviado correctamente.");
            
        } catch (AlumnoNoEncontradoException e) {
        	
            return ResponseEntity.status(404).body(e.getMessage()); // 404 Not Found
            
        } catch (ProfesorNoEncontradoException e) {
        	
            return ResponseEntity.status(404).body(e.getMessage()); // 404 Not Found
            
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage()); // 500 Internal Server Error
        }
    }
}

*/
