package es.daw.proyectoDAW.controladores;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.daw.proyectoDAW.errores.AlumnoNoEncontradoException;
import es.daw.proyectoDAW.modelo.Clase;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.servicio.IFServicioProfesor;
import es.daw.proyectoDAW.servicio.ServicioClase;

////***************************************************IMPORTS

@RestController

public class ControladorProfesor {

	//// ENLAZAMOS CON EL SERVICIO
	@Autowired
	private IFServicioProfesor  serviUsuario;
	
	@Autowired
	private ServicioClase serviClase;


	// ----------------------------------------------------------------------------CREATE

	/// anadir un alumno nuevo alumno
	@PostMapping("/aniadiralumno")
	public ResponseEntity<?> anyadirAlumno(@RequestBody Usuario alumno) {
		
		try {
	    // Validar si los datos necesarios están presentes en el JSON
			 if (alumno.getClase() == null || alumno.getClase().getLetraClase() == null || alumno.getClase().getCentro() == null) {
			        return ResponseEntity.badRequest().body(null);
			    }
			 
			// Validar que el NIA no exista previamente en la base de datos
		        if (serviUsuario.findByNiaAlumno(alumno.getNiaAlumno()).isPresent()) {
		        	// NIA ya existe
		            return ResponseEntity.status(HttpStatus.CONFLICT).body(" El NIA ya está registrado");  
		        }
	    
	    // Validar que la letraClase existe en la base de datos
        String letraClase = alumno.getClase().getLetraClase();
        Clase claseExistente = serviClase.obtenerClasePorLetra(letraClase);
        
        if (claseExistente == null) {
        	// Clase no encontrada 
        	return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);  
        
        }
         
        // Asignar la clase existente al alumno
        alumno.setClase(claseExistente);
        
	    // Aseguramos que el rol es "ALUMNO"
        alumno.setRolUsuario("alumno");
        
        // Guardar el nuevo alumno en la base de datos
        Usuario nuevoAlumno = serviUsuario.aniadeUsuarioAlumno(alumno);
        
        // Confirmar que el alumno fue guardado correctamente
        if (nuevoAlumno != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoAlumno.getIdUsuario())
                .toUri();
            return ResponseEntity.created(location).body(nuevoAlumno);
        }
        // Si no se pudo guardar por algún motivo
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        
		 } catch (Exception e) {
		        // Manejo de errores inesperados
		        e.printStackTrace();
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
		} 

	// ----------------------------------------------------------------------------READ

	/// obtener todos los alumnos
	@GetMapping("/todousuarios")
	public ResponseEntity<?> obtenerUsuarios() {

		List<Usuario> listaUsuarios = serviUsuario.obtenerUsuarios();

		if (!listaUsuarios.isEmpty()) {
			return ResponseEntity.ok(listaUsuarios);

		} else {

			return ResponseEntity.notFound().build();
		}

	}
	
	/// obtener todos los alumnos
	  @GetMapping("/nia/{nia}")
	    public ResponseEntity<?> obtenerPorNia(@PathVariable String niaAlumno) {
	        System.out.println("Valor recibido de NIA: " + niaAlumno); // Para depurar el valor del NIA recibido

	        Optional<Usuario> usuario = serviUsuario.findByNiaAlumno(niaAlumno);

	        if (usuario.isPresent()) {
	            return ResponseEntity.ok(usuario.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	// -----------------------------------------------------------------

	/// obtener todos los alumnos por la letra de la clase
	@GetMapping("/obtener_alumnos_clase")

	public ResponseEntity<List<Usuario>> obtenerAlumnosPorClase(
			@RequestParam String letraClase) {

		if (letraClase == null || letraClase.isEmpty()) {
	        return ResponseEntity.badRequest().body(null); // Código 400 si no se proporciona la letra
	    }
		
		 // Buscar directamente los alumnos por la letra de clase
	    List<Usuario> alumnos = serviUsuario.obtenerAlumnosPorLetra(letraClase);
	    
	    
	    if (alumnos.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // Código 204 si no hay alumnos
	    }
	    
	    
	    return ResponseEntity.ok(alumnos);
	    }

	// -----------------------------------------------------------------
	/// obtener la letra de clase del profesor recibido
	@PostMapping("/pedirLetraProfesor")
	public ResponseEntity<?> obtenerLetraProfesor(@RequestBody String emailProfesor) {

	    if (emailProfesor == null || emailProfesor.isEmpty()) {
	        return ResponseEntity.badRequest().body(Map.of("error", "El email del profesor es obligatorio"));
	    }

	    try {
	        // Llamamos al servicio para obtener la clase del profesor
	        Optional<Clase> claseUsuario = serviUsuario.obtenerClasePorEmail(emailProfesor);

	        if (claseUsuario.isPresent()) {
	            // Si el profesor tiene una clase asignada, devolvemos la letra de clase
	            Clase clase = claseUsuario.get();
	            String letraClase = clase.getLetraClase();

	            if (letraClase != null && !letraClase.isEmpty()) {
	                return ResponseEntity.ok(Map.of("letraClase", letraClase));
	            } else {
	                return ResponseEntity.status(HttpStatus.NO_CONTENT)
	                        .body(Map.of("message", "El profesor no tiene una clase asignada o la letra está vacía"));
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body(Map.of("error", "No se encontró ninguna clase para el email proporcionado"));
	        }
	    } catch (Exception e) {
	    	System.err.println("Error al obtener la letra de la clase para el email: " + emailProfesor);
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Map.of("error", "Ocurrió un error al procesar la solicitud"));
	    }
	}

//----------------------------------------------------------------------------DELETE POR ID

	
	  ///eliminar un usuario por NIA
	  
	@DeleteMapping("/borrar_usuario_nia/{nia}")
	public ResponseEntity<?> eliminarUsuarioPorNIA(@PathVariable  String nia) throws AlumnoNoEncontradoException {
	   
		System.out.println("Solicitud de baja para NIA: " + nia);  // Para verificar si el NIA llega correctamente
	   
		Optional<Usuario> usuarioEliminator;
		try {
						usuarioEliminator = serviUsuario.borrarUsuarioPorNIA(nia);

			    	//si se elimina el alumno, se devuelve el obj
			        return ResponseEntity.ok(usuarioEliminator.get());
			        
			    
			
		} catch (AlumnoNoEncontradoException e) {
			e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                .body("Alumno no encontrado");
		}

	  
	}

	// ----------------------------------------------------------------- DELETE POR
	// MAIL
	/// eliminar un usuario por mail
	@DeleteMapping("/borrar_usuario_mail/{mailUsuario}")
	public ResponseEntity<Usuario> eliminarUsuarioPorMail(@PathVariable String mailUsuario) {

		/// NOS ASEGURAMOS DE QUE EXISTE PREVIAMENTE ESE USUARIO
		Optional<Usuario> alumnoParaBorrarMail = serviUsuario.borrarUsuarioPorMail(mailUsuario);

		if (alumnoParaBorrarMail.isPresent()) {

			// CREAMOS URI
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

			/// SI EXISTE DEVOLVEMOS EN LA RESPUESTA EL ALUMNO ELIMINADO
			return ResponseEntity.ok().location(location).body(alumnoParaBorrarMail.get());

		} else {

			/// SI NO EXISTE DEVOLVEMOS EN LA RESPUESTA QUE NO EXISTE
			return ResponseEntity.notFound().build();
		}
	}

//----------------------------------------------------------------------------UPDATE

	@PutMapping("/modificamos_datos_usuario/{mailUsuario}")
	public ResponseEntity<?> modificamosDatosUser(@RequestBody Usuario alum, @PathVariable String mailUsuario) {
		// ACTUALIZAMOS LOS DATOS DEL USUARIO POR SU CORREO ELECTRÓNICO
		Optional<Usuario> alumnoParaModificar = serviUsuario.actualizarUsuarioPorMail(mailUsuario, alum);

		if (alumnoParaModificar.isPresent()) {
			
			 // OBTENEMOS EL USUARIO ACTUALIZADO
	        Usuario usuarioModificado = alumnoParaModificar.get();
	        
			// CONSTRUIMOS URI
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{mailUsuario}")
					.buildAndExpand(mailUsuario).toUri();

			// SI SE ACTUALIZÓ EL USUARIO, DEVOLVEMOS LA RESPUESTA CON LA UBICACIÓN DEL
			// RECURSO MODIFICADO
			return ResponseEntity.ok().location(location).body(usuarioModificado);
		} else {
			// SI NO SE ENCONTRÓ EL USUARIO, DEVOLVEMOS UNA RESPUESTA 404
			return ResponseEntity.status(404).body("Usuario no encontrado");
		}
	}

}// -----------FIN CONTROLADOR ControladorProfesor