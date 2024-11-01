package es.daw.proyectoDAW.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import es.daw.proyectoDAW.servicio.ServicioEmail;
import es.daw.proyectoDAW.servicio.ServicioProfesor;

////***************************************************IMPORTS


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ControladorAlumno {
	
	
	////ENLAZAMOS  SERVICIO DE CORREO
//	@Autowired ServicioEmail serviMail;
	
	
	
	///METODO PARA ACCEDER AUTOMATICAMENTE AL INICIO DE LA APP
			public class accesoAPP {
			    @RequestMapping("/")
				    public String index() {
				        return "index";
				    }
			}
			
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
			//----------------------------------------------------------------------------UPDATE
			//----------------------------------------------------------------------------DELETE





}
