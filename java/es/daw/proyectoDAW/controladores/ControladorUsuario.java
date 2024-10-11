package es.daw.proyectoDAW.controladores;

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

import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.servicio.ServicioProfesor;



////***************************************************IMPORTS



@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ControladorUsuario {


	////ENLAZAMOS CON EL SERVICIO
	@Autowired
		private ServicioProfesor repoUsuario ;
	
	////ENLAZAMOS CON EL CONTROLADOR DE RUTAS
	@Autowired
	private ControladorRutas controladorRutas;
	
    private static final Logger logger = LoggerFactory.getLogger(ControladorUsuario.class);
	
//-----------------------------------------------------------------

	///METODO PARA ACCEDER AUTOMATICAMENTE AL INICIO DE LA APP
			public class accesoAPP {
			    @RequestMapping("/")
				    public String index() {
				        return "index";
				    }
			}
			
			
	//-----------------------------------------------------------------
				 @PostMapping("/verificacion")
				    public ResponseEntity<?> validarUserAndPass(@RequestBody Usuario usuario) {
				        logger.info("Solicitud recibida: {}", usuario);
	
				        Usuario usuarioConMail = repoUsuario.findByMailAndPass(usuario.getMailUsuario(), usuario.getPassUsuario());
	
				        if (usuarioConMail != null) {
				            String rolUsuario = usuarioConMail.getRolUsuario();
				            String redirectUrl = "";
	
				            switch (rolUsuario) {
				                case "alumno":
				                    redirectUrl = "/alumno";
				                    break;
				                case "profesor":
				                    redirectUrl = "/profesor";
				                    break;
				                default:
				                    redirectUrl = "/rediLogin";
				                    break;
				            }
	
				            return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
				        } else {
				            return ResponseEntity.badRequest().body("El correo electrónico o la contraseña son incorrectos");
				        }
				    }
	
		
	
}//-----------FIN CONTROLADOR ControladorUsuario