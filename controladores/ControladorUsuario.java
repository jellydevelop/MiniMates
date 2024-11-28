package es.daw.proyectoDAW.controladores;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.servicio.ServicioProfesor;
import es.daw.proyectoDAW.servicio.ServicioUsuario;



////***************************************************IMPORTS



@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ControladorUsuario {

	
	////ENLAZAMOS CON EL SERVICIO
	@Autowired
		private ServicioUsuario repoUsuario ;
	
	@Autowired
	private ControladorRutas rutas ;
	
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
				            Map<String, String> response = new HashMap<>();
				            response.put("redirectUrl", redirectUrl);
				            
				            
				            return ResponseEntity.ok().body(response);
				        } else {
				            return ResponseEntity.badRequest().body(Map.of("message", "El correo electrónico o la contraseña son incorrectos"));			        }
				    }
	
	//-----------------------------------------------------------------
			/*	 @PutMapping("/{usuarioId}/clase/{letraClase}")
				    public ResponseEntity<String> asignarClase(
				            @PathVariable Long usuarioId, @PathVariable String letraClase) {
				        
				        try {
				            repoUsuario.asignarUsuarioAClasePorLetra(usuarioId, letraClase);
				            return ResponseEntity.ok("Usuario asignado a la clase con letra " + letraClase);
				        } catch (Exception e) {
				            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
				        }
				    }
	*/
}//-----------FIN CONTROLADOR ControladorUsuario