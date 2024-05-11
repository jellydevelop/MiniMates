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
	//	METODO PRARA CREAR MODELO
			
			Usuario alumno=new Usuario();
			
			   @GetMapping("/formularioAlta")
			    public String mostrarFormularioAlta(Model model) {
				   
			        
			        // Añadir el objeto Usuario al modelo para que esté disponible en la vista
			        model.addAttribute("alumno", alumno);
			        
			        // Devolver el nombre de la vista Thymeleaf que mostrará el formulario
			        return "formularioAlta";
			    }
			   
			   
			   @GetMapping("/ruta_que_muestra_la_vista")
			   public String mostrarVista(Model model) {
			       Usuario alumno = new Usuario(); // Crear una instancia de Usuario
			       model.addAttribute("alumno", alumno); // Agregar el objeto alumno al modelo
			       return "datos_alumno_profesor"; // Devolver el nombre de la vista
			   }
	
	//----------------------------------------------------------------------------CREATE
		
		/*	///añadir alumno sin profesor
			@PostMapping("/aniadiralumno")
			public ResponseEntity<Usuario> anyadirAlumno(@RequestBody Usuario alum){
				
				///ALMACENAMOS EN UN OBEJTO USUARIO LA INFIRMACION RECIBIDA EB LA PETCICION
					Usuario nuevoAlum = repoUsuario.aniadeUsuarioAlumno(alum);
					
				/// VALIDAMOS INFO RECIBIDA Y PASAMOS A DEVOLVER RESPUESTA
				
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
			 }*/
			
		    @PostMapping("/aniadiralumno")
		    public ResponseEntity<String> anyadirAlumno(@RequestBody Usuario alumno)  {
		       
		    	try {
		        // ALMACENAMOS EN UN OBJETO USUARIO LA INFORMACIÓN RECIBIDA EN LA PETICIÓN
		        Usuario nuevoAlum = repoUsuario.aniadeUsuarioAlumno(alumno);
		        
		        // VALIDAMOS INFO RECIBIDA Y PASAMOS A DEVOLVER RESPUESTA
		        if (nuevoAlum != null) {
		            // Si se agregó correctamente, redirige a una página de éxito
		        	return ResponseEntity.ok("Alumno añadido correctamente");
		        } else {
		            // Si no se agregó correctamente, redirige a una página de error
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al añadir el alumno");
		        }
		    
		    	} catch (Exception e) {
		            // Manejo de la excepción
		            e.printStackTrace(); // Imprime el rastreo de la pila de la excepción en la consola
		            // Devolvemos una respuesta con código 500 (Error interno del servidor)
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
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

			///eliminar un usuario  por ID
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
			}
	
	//-----------------------------------------------------------------
			///eliminar un usuario  por mail
			@DeleteMapping("/borrar_usuario_mail/{mailUsuario}")
				public ResponseEntity<Usuario> eliminarUsuarioPorMail(@PathVariable String mailUsuario ){
				
				/// NOS ASEGURAMOS DE QUE EXISTE PREVIAMENTE ESE USUARIO
					Optional<Usuario> alumnoParaBorrarMail=repoUsuario.borrarUsuarioPorMail(mailUsuario );
					
					if(alumnoParaBorrarMail.isPresent()) {
						
						/// SI EXISTE DEVOLVEMOS EN LA RESPUESTA EL ALUMNO ELIMINADO
						return ResponseEntity.ok(alumnoParaBorrarMail.get());
						
					}else {
						
						/// SI NO EXISTE DEVOLVEMOS EN LA RESPUESTA QUE NO EXISTE
						return ResponseEntity.notFound().build();
					}
			}
			

			
//----------------------------------------------------------------------------UPDATE
			
			///actualizamos algun dato de uno del usuario por ID
			@PutMapping("/modificamos_datos_usuario/{id}")
				public ResponseEntity<Usuario> modificamosDatosUser(@RequestBody Usuario alum, @PathVariable Long id){
				
				/// NOS ASEGURAMOS DE QUE EXISTE PREVIAMENTE ESE USUARIO
				Optional<Usuario> alumnoParaModificar=repoUsuario.actualizarUsuarioPorId(id,alum);
				
			    if(alumnoParaModificar.isPresent()) {
					
					 // CONSTRUIMOS URI
		            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		            										.path("/{id}")
		                                                    .build()
		                                                    .toUri();		                                					

					/// SI EXISTE DEVOLVEMOS EN LA RESPUESTA EL ALUMNO MODIFICADO
		            return ResponseEntity.ok().location(location).build();
		            
		        } else {
		        	
		            return ResponseEntity.notFound().build(); 
		            
		        }		
				
			}

		
				
			
			
}//-----------FIN CONTROLADOR ControladorProfesor