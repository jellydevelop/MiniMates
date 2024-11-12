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
	
		
	  //------------------------>>>> LOGIN

		    @GetMapping("/login")
		    public String mostrarLogin() {
		    	
		        return "login";
		    }
	    
	 //------------------------>>>> GESTION DE DATOS DE ALUMNOS

		    @GetMapping("/datosAlumnnoProfesor")
		    public String mostrarDatosAlumnoProfesor() {
		    	
		        return "datos_alumno_profesor";
		    }
	 //------------------------>>>> INICIO APP
	    
	    @GetMapping("/index")
	    public String mostrarIndex() {
	    	
	        return "index";
	    }
	    
	//------------------------>>>> MENU PROFESOR
	    
		    @GetMapping("/profesor")
		    public String mostrarProfesor() {
		    	
		        return "profesor";
		    }
	
	//------------------------>>>> MENU ALUMNO
			
		    @GetMapping("/alumno")
		    
		    public String mostrarAlumno() {
		    	
		        return "alumno";
		    }
		    
	//------------------------>>>> FIN PARTIDA
			
		    @GetMapping("/descanso")
		    
		    public String mostrarDescanso() {
		    	
		        return "descansoAPP";
		    }
		    
	//------------------------>>>> INTRODUCCION A M1
			
		    @GetMapping("/introMundo1")
		    
		    public String mostrarIntroM1() {
		    	
		        return "introMundo1_pagi4";
		    }
		    
	 //------------------------>>>> REDIRIGIR DESDE LOGIN
	
		    @GetMapping("/rediLogin")
		    
		    public String mostrarRedireccion() {
		    	
		        return "redi";
		    }
		    
	    
		              	
		  	    
	// ------------------------>>>> REDIRIGIR DESDE MENU PROFESOR

		    @GetMapping("/rediMenuProfesor")
		    public String redireccionar(@RequestParam("opcion") String opcion) {
		    	
		        if (opcion == null) {
		            return "/notFound";
		            
		        } else {
		            switch (opcion) {
		            
		                case "listaAlumnos":
		                	
		                    return "/listaAlumnos";
		                    
		                case "datosAlumno":
		                	
		                    return "/datosAlumnnoProfesor";
		                    
		                default:
		                    return "/notFound";
		            }
		        }
		    }

		    
	//------------------------>>>> LISTADO DE ALUMNOS + STATS PARA PROFESOR
			
		    @GetMapping("/listaAlumnos")
		    
		    public String mostrarlistaAlumsStats() {
		    	
		        return "lista_alumnos_estadisticas";
		    }

	//------------------------>>>> STATS DEL PROPIO ALUMNO
			
		    @GetMapping("/tus_estadisticas")
		    
		    public String mostrarAlumsStats() {
		    	
		        return "tus_estadisticas";
		    }
		    
	//------------------------>>>> MAPA MUNDO1 reto1
			
		    @GetMapping("/mapaMundo1P1")
		    
		    public String mostrarM1P1() {
		    	
		        return "mapaMundo_pag5";
		    }
		    
	//------------------------>>>> MAPA MUNDO1 reto2
			
		    @GetMapping("/mapaMundo1P2")
		    
		    public String mostrarM1P2 (){
		    	
		        return "mapaMundo_pag7";
		    }
	//------------------------>>>> MAPA MUNDO1 reto2
			
		    @GetMapping("/mapaMundo1Fin")
		    
		    public String mostrarM1Fin (){
		    	
		        return "mapaMundo1_Fin";
		    }
		        
	//------------------------>>>> NOT FOUND 404
			
		    @GetMapping("/notFound")
		    
		    public String mostrarNotFound (){
		    	
		        return "notFound";
		    }
		    
	//------------------------>>>> RETO 1 - MUNDO 1
			
		    @GetMapping("/reto1M1")
		    
		    public String mostrarReto1M1 (){
		    	
		        return "pantalla1_M1";
		    }
		    
	//------------------------>>>> RETO 2 - MUNDO 1
			
		    @GetMapping("/reto2M1")
		    
		    public String mostrarReto2M1 (){
		    	
		        return "pantalla2_M1";
		    }
		    
	//------------------------>>>> PRESENTACION APP
			
		    @GetMapping("/presentacionMati")
		    
		    public String mostrarMati (){
		    	
		        return "pagina3_presentacion";
		    }
		    
		  //------------------------>>>> MENU PROFESOR
		    
		    @GetMapping("/contacto")
		    public String mostrarFormContacto() {
		    	
		        return "contacto";
		    }
		    
		    
	}////// FIN   COntrolador RUtas