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

import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.servicio.ServicioProfesor;





////***************************************************IMPORTS


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ControladorProfesor {


	////ENLAZAMOS CON EL SERVICIO
	@Autowired
		private ServicioProfesor repoUsuario ;
	
	///METODO PARA ACCEDER AUTOMATICAMENTE AL INICIO DE LA APP
			public class accesoAPP {
			    @RequestMapping("/")
				    public String index() {
				        return "index";
				    }
			}
	
	
	//----------------------------------------------------------------------------CREATE
		
			
		    @PostMapping("/aniadiralumno")
		    public ResponseEntity<Usuario> anyadirAlumno(@RequestBody Usuario alumno)  {
		       
		        // ALMACENAMOS EN UN OBJETO USUARIO LA INFORMACIÓN RECIBIDA EN LA PETICIÓN
		        Usuario nuevoAlum = repoUsuario.aniadeUsuarioAlumno(alumno);
		        
				if (nuevoAlum != null) {

				        // CREAMOS URI
			            URI location = ServletUriComponentsBuilder
			                            .fromCurrentRequest()
			                            .build()
			                            .toUri();
	            
	            // DEVOLVEMOS RESPUESTA CON URI + ALUMNO CREADO
	            	return ResponseEntity.created(location).body(nuevoAlum);
	            
		    		} else {
	        	
	            /// SI EL USUARIO NO TIENE INFO PREPARAMOS CUERPO PARA CLIETNE
	        		return ResponseEntity.noContent().build();
		    	}
		    
		    	
		    }
		
				
				
		
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

			
			///obtener todos los alumnos por de un profesor
			@GetMapping("/obtener_alumnos_clase")
				public ResponseEntity<?> obtenerAlumnosDeClaseDeUnProfesor(){
				
				 Optional<List<Usuario>> listaAlumnos=repoUsuario.obtenerUsuariosPorRol("alumno");
						
							if (listaAlumnos.isPresent()) {
								
								return ResponseEntity.ok(listaAlumnos.get());
							
							}else {
							
								return ResponseEntity.notFound().build();				
							}
						
			}
			/////////////////////LO IDEAL ES LUEGO REALIZAR UNA BUSQUEDA POR PROFESOR, PERO POR AHORA
			//////NO SE ASOCIAN LOS ALUMNOS CON LOS PROFESORES
		
//----------------------------------------------------------------------------DELETE POR ID

		/*	///eliminar un usuario  por ID
			@DeleteMapping("/borrar_usuario/{id}")
				public ResponseEntity<Usuario> eliminarUsuarioPorId(@PathVariable Long id){
				
				/// NOS ASEGURAMOS DE QUE EXISTE PREVIAMENTE ESE USUARIO
					Optional<Usuario> alumnoParaBorrar=repoUsuario.borrarUsuarioPorId(id);
					
					if(alumnoParaBorrar.isPresent()) {
						
						/// SI EXISTE DEVOLVEMOS EN LA RESPUESTA EL ALUMNO ELIMINADO
						return ResponseEntity.ok(alumnoParaBorrar.get());
						
					}else {
						
						/// SI NO EXISTE DEVOLVEMOS EN LA RESPUESTA QUE NO EXISTE
						return ResponseEntity.notFound().build();
					}
			}*/
	
	//----------------------------------------------------------------- DELETE POR MAIL 
			///eliminar un usuario  por mail
			@DeleteMapping("/borrar_usuario_mail/{mailUsuario}")
				public ResponseEntity<Usuario> eliminarUsuarioPorMail(@PathVariable String mailUsuario ){
				
				/// NOS ASEGURAMOS DE QUE EXISTE PREVIAMENTE ESE USUARIO
					Optional<Usuario> alumnoParaBorrarMail=repoUsuario.borrarUsuarioPorMail(mailUsuario );
					
					if(alumnoParaBorrarMail.isPresent()) {
						
						// CREAMOS URI
			            URI location = ServletUriComponentsBuilder
			                            .fromCurrentRequest()
			                            .build()
			                            .toUri();
			            
						/// SI EXISTE DEVOLVEMOS EN LA RESPUESTA EL ALUMNO ELIMINADO
						return ResponseEntity.ok().location(location).body(alumnoParaBorrarMail.get());
						
					}else {
						
						/// SI NO EXISTE DEVOLVEMOS EN LA RESPUESTA QUE NO EXISTE
						return ResponseEntity.notFound().build();
					}
				        }
			
			

			
//----------------------------------------------------------------------------UPDATE
			
			@PutMapping("/modificamos_datos_usuario/{mailUsuario}")
			public ResponseEntity<Usuario> modificamosDatosUser(@RequestBody Usuario alum, @PathVariable String mailUsuario) {
			    // ACTUALIZAMOS LOS DATOS DEL USUARIO POR SU CORREO ELECTRÓNICO
			    Optional<Usuario> alumnoParaModificar = repoUsuario.actualizarUsuarioPorMail(mailUsuario, alum);

			    if (alumnoParaModificar.isPresent()) {
			        // CONSTRUIMOS URI
			        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			                .path("/{mailUsuario}")
			                .buildAndExpand(mailUsuario)
			                .toUri();

			        // SI SE ACTUALIZÓ EL USUARIO, DEVOLVEMOS LA RESPUESTA CON LA UBICACIÓN DEL RECURSO MODIFICADO
			        return ResponseEntity.ok().location(location).build();
			    } else {
			        // SI NO SE ENCONTRÓ EL USUARIO, DEVOLVEMOS UNA RESPUESTA 404
			        return ResponseEntity.notFound().build();
			    }
			}

		
				
			
			
}//-----------FIN CONTROLADOR ControladorProfesor