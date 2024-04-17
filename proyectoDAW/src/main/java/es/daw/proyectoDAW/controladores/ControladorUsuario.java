package es.daw.proyectoDAW.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.servicio.ServicioUsuario;





////***************************************************IMPORTS


@RestController
public class ControladorUsuario {


	////ENLAZAMOS CON EL SERVICIO
	@Autowired
		private ServicioUsuario repoUsuario ;
	
	
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
			 @GetMapping("/redireccionar")
			    public ResponseEntity<String> redireccionarUsuario(@RequestParam("nombreUsuario") String nombreUsuario) {
			       
				 String rol = repoUsuario.obtenerRolUsuario(nombreUsuario);
				 
			        if ("profesor".equals(rol)) {
			        	
			            return ResponseEntity.ok("redirect:/profesor.html");
			            
			            
			        } else if ("alumno".equals(rol)) {
			        	
			            return ResponseEntity.ok("redirect:/alumno.html");
			            
			        } else {
			        	
			            return ResponseEntity.ok("redirect:/redi.html");

			        }
			    }
			
			
			
}//-----------FIN CONTROLADOR ControladorUsuario