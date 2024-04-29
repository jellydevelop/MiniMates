package es.daw.proyectoDAW.controladores;

import java.util.List;
import java.util.Optional;

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

import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.servicio.ServicioUsuario;





////***************************************************IMPORTS


@RestController
@CrossOrigin(origins = "http://localhost")
public class ControladorUsuario {


	////ENLAZAMOS CON EL SERVICIO
	@Autowired
		private ServicioUsuario repoUsuario ;
	
	///METODO PARA ACCEDER AUTOMATICAMENTE AL INICIO DE LA APP
			public class accesoAPP {
			    @RequestMapping("/")
				    public String index() {
				        return "index";
				    }
			}
			
	
	//----------------------------------------------------------------------------CREATE
		/*///añadir alumno y asociarlo a grupo 
		@PostMapping("/anyadiralumno/{idGrupo}")
		public ResponseEntity<Alumno> anyadirAlumnoConGrupo(@PathVariable Long idGrupo,@RequestBody Alumno alumno){
			
		
			  // Busca el grupo en el repositorio
		    Optional<Grupo> grupoExistente = repositorioGrupos.listaGruposID(idGrupo);

		    // Verifica si el grupo existe
		    if (grupoExistente.isPresent()) {
		        // Recupera el grupo del Optional
		        Grupo grupo = grupoExistente.get();
		        
		        // Asocia el alumno al grupo
		        alumno.setGrupo(grupo);

		        // Añade el alumno con el grupo asociado
		        Alumno nuevoAlumno = repositorioAlumnos.aniadeAlumnoConGrupo(alumno, idGrupo);

		        // Construye la URI para el nuevo recurso (el alumno)
		        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		                                                  .path("/{id}")
		                                                  .buildAndExpand(nuevoAlumno.getId())
		                                                  .toUri();
		        
		        // Devuelve una respuesta con el código 201 Created y la URI del nuevo recurso
		        return ResponseEntity.created(location).body(nuevoAlumno);
		    } else {
		        // Si el grupo no existe, devuelve una respuesta con el código 404 Not Found
		        return ResponseEntity.notFound().build(); 
		    }   
		}
		*/
			
	//----------------------------------------------------------------------------READ
		
		

			
			///obtener todos los alumnos
			@GetMapping("/todousuarios")
				public ResponseEntity<?> obtenerUsuarios(){
				
						List<Usuario> listaUsuarios=repoUsuario.obtenerUsuarios();
						
							if (!listaUsuarios.isEmpty()) {							
								return ResponseEntity.ok(listaUsuarios);
							
							}else {
							
								return ResponseEntity.notFound().build();				
							}
						
			}
			
		//-----------------------------------------------------------------
			/*@GetMapping("/redireccion/{idUsuario}")
			
		    public ResponseEntity<String> redireccionarUsuario(@PathVariable Long idUsuario) {
		       
				String rol = repoUsuario.obtenerRolUsuario(idUsuario);
		       
				if (rol.equals("alumno")) {
		        
					return ResponseEntity.ok("redirect:/alumno.html");
		       
				} else if (rol.equals("profesor")) {
		          
					return ResponseEntity.ok("redirect:/profesor.html");
		       
				} else {
		            
					return ResponseEntity.ok("redirect:/redi.html");
		        }
		    }*/
			
		//-----------------------------------------------------------------
			@PostMapping("/verificacion")
			public ResponseEntity<?> validarUserAndPass(@RequestBody Usuario user) {
				
			    Optional<Usuario> usuarioChecked = repoUsuario.findByMail(user.getMail());

			    if (usuarioChecked.isPresent()) {
			    	
			        Usuario usuario = usuarioChecked.get();

			        // Verificar contraseña
			        if (!usuario.getPassUsuario().equals(user.getPassUsuario())) {
			        	
			            return ResponseEntity.badRequest().body("La contraseña es incorrecta");
			        }

			        // URL de redirección según el rol del usuario
			        String redirectUrl = "";
			        
				        switch (usuario.getRolUsuario()) {
				            case "alumno":
				                redirectUrl = "/alumno";
				                break;
				            case "profesor":
				            	  redirectUrl = "/profesor";
				                break;
				            default:
				            	  redirectUrl = "/redi";
				                break;
				        }

				     return ResponseEntity.ok(redirectUrl);
			   
			    } else {
			        return ResponseEntity.badRequest().body("El correo electrónico del usuario es incorrecto");
			    }
			}

		
				       
				
				
			
			
}//-----------FIN CONTROLADOR ControladorUsuario