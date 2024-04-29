package es.daw.proyectoDAW.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//--------------------------------------------------------

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
	        // Devuelve el nombre del archivo HTML de la página de inicio
	        return "profesor";
	    }
 //------------------------

	    @GetMapping("/alumno")
	    public String mostrarAlumno() {
	        // Devuelve el nombre del archivo HTML de la página de inicio
	        return "alumno";
	    }
	    
	  //------------------------

	    @GetMapping("/redireccion")
	    public String mostrarRedireccion() {
	        // Devuelve el nombre del archivo HTML de la página de inicio
	        return "redi";
	    }
    
}