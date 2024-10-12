
package es.daw.proyectoDAW.controladores;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.daw.proyectoDAW.modelo.Mundo;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.servicio.ServicioMundo;
import es.daw.proyectoDAW.servicio.ServicioProfesor;



////***************************************************IMPORTS
@CrossOrigin(origins = "http://localhost:8080")
@RestController


public class ControladorMundo {



	////ENLAZAMOS CON EL SERVICIO MUNDO
	@Autowired
		private ServicioMundo repoMundo ;
	
	
	
	
	//----------------------------------------------------------------------------CREATE
		
				
				
		
	//----------------------------------------------------------------------------READ
		
			///obtener todos los mundos
			@GetMapping("/mundos")
				public ResponseEntity<?> obtenerMundos(){
				
						List<Mundo> listaMundos=repoMundo.obtenerMundos();
						
							if (!listaMundos.isEmpty()) {							
								return ResponseEntity.ok(listaMundos);
							
							}else {
							
								return ResponseEntity.notFound().build();				
							}
						
			}
			
			//-----------------------------------------------------------------

	/*	////obtener pantallas de 1 mundo
			@GetMapping("/pantallasDeUnMundo/{idMundo}")
				public ResponseEntity<?> obtenerAllPantallasMundo(@PathVariable Long idMundo){
				
						List<Mundo> listaPantallas=repoMundo.obtenerAllPantallasOneMundo(idMundo);
						
							if (!listaPantallas.isEmpty()) {							
								return ResponseEntity.ok(listaPantallas);
							
							}else {
							
								return ResponseEntity.notFound().build();				
							}
						
			}
			
			//-----------------------------------------------------------------

			///obtener 1 pantalla de 1 mundo
			@GetMapping("/unaPantallasDeUnMundo/{idMundo}")
				public ResponseEntity<?> obtenerAllPantallasMundo(@PathVariable Long idMundo){
				
						List<Mundo> listaPantallas=repoMundo.obtenerAllPantallasOneMundo(idMundo);
						
							if (!listaPantallas.isEmpty()) {							
								return ResponseEntity.ok(listaPantallas);
							
							}else {
							
								return ResponseEntity.notFound().build();				
							}
						
			}
		
		*/
//----------------------------------------------------------------------------DELETE 

	
	
			

			
//----------------------------------------------------------------------------UPDATE
			

		
				
			
			
}//-----------FIN CONTROLADOR ControladorMundo
