	package es.daw.proyectoDAW.controladores;
	
	import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PostMapping;
	//--------------------------------------------------------
	import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@Controller
	public class ControladorRutas {
	
	    @GetMapping("/login")
	    public String mostrarLogin() {
	        // Devuelve el nombre del archivo HTML de la página de login
	        return "login";
	    }
	    
	    //------------------------
	    
	    @GetMapping("/index")
	    public String mostrarIndex() {
	        // Devuelve el nombre del archivo HTML de la página de inicio
	        return "index";
	    }
	    
	//------------------------
	    
		    @GetMapping("/profesor")
		    public String mostrarProfesor() {
		        return "profesor";
		    }
	 //------------------------
	
		    @GetMapping("/alumno")
		    public String mostrarAlumno() {
		        return "alumno";
		    }
		    
		  //------------------------
	
		    @GetMapping("/redireccion")
		    public String mostrarRedireccion() {
		        return "redi";
		    }
		    
		 //------------------------
		    
		    @GetMapping("/redireccion_menu__profesor")
		    public String redireccionar(@RequestParam("opcion") String opcion) {
		    	
		    	if (opcion == null) {
		            // Si no se proporciona ninguna opción, puedes redirigir al usuario a una página de error o mostrar un mensaje adecuado.
		            return "notFound";
		            
		        } else {
		        	
		        
		            if (opcion.equals("estadisticas")) {
		            	
		            	
		            
		                return "lista_alumnos_estadisticas";
		                
		            } else if (opcion.equals("datosAlumno")) {
		            	
		            
		                return "datos_alumno_profesor";
		                
		            } else if (opcion == null) {
		                // Si no se proporciona ninguna opción, puedes redirigir al usuario a una página de error o mostrar un mensaje adecuado.
		                return "notFound";
		            }else {
		            	return null;
		            }
		        }
		    }
	}////// FIN   COntrolador RUtas