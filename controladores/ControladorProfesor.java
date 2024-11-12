package es.daw.proyectoDAW.controladores;

import java.net.URI;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

import es.daw.proyectoDAW.errores.ClaseNoEncontradaException;
import es.daw.proyectoDAW.modelo.Centro_Educativo;
import es.daw.proyectoDAW.modelo.Clase;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.repositorio.RepositorioClase;
import es.daw.proyectoDAW.servicio.ServicioProfesor;

////***************************************************IMPORTS

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class ControladorProfesor {

	//// ENLAZAMOS CON EL SERVICIO
	@Autowired
	private ServicioProfesor repoUsuario;
	
	@Autowired
	private RepositorioClase repoClases;

	/// METODO PARA ACCEDER AUTOMATICAMENTE AL INICIO DE LA APP
	public class accesoAPP {
		@RequestMapping("/")
		public String index() {
			return "index";
		}
	}

	// ----------------------------------------------------------------------------CREATE

	/// anadir un alumno nuevo alumno
	@PostMapping("/aniadiralumno")
	public ResponseEntity<Usuario> anyadirAlumno(@RequestBody Usuario alumno) {
	    // Validar si los datos necesarios están presentes en el JSON
	    if (alumno.getClase() == null || alumno.getClase().getLetraClase() == null || alumno.getClase().getCentro() == null) {
	        return ResponseEntity.badRequest().build();  // Falta la clase, letra o centro
	    }

	    // Obtener los datos de clase y centro
	    String letraClase = alumno.getClase().getLetraClase();
	    Centro_Educativo centro = alumno.getClase().getCentro();

	    // Buscar el ID de la clase usando letraClase
	    Long idClase = repoClases.findIdClaseByLetraClase(letraClase);  // Consulta que devuelve solo el ID de la clase

	    // Si no existe la clase, devolvemos un 404
	    if (idClase == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Clase no encontrada
	    }

	    // Crear un objeto de clase con el ID encontrado
	    Clase claseExistente = new Clase();
	    claseExistente.setIdClase(idClase);  // Asignamos el ID de la clase a la clase encontrada

	    // Asignar la clase encontrada al alumno
	    alumno.setClase(claseExistente);

	    // Guardar el alumno en la base de datos
	    Usuario nuevoAlum = repoUsuario.aniadeUsuarioAlumno(alumno);

	    // Si el alumno se guardó correctamente, devolvemos la URI de creación con un 201
	    if (nuevoAlum != null) {
	        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
	        return ResponseEntity.created(location).body(nuevoAlum);  // Devolver el alumno creado
	    }

	    // Si hubo un error al guardar el alumno, devolvemos un 204 (No Content)
	    return ResponseEntity.noContent().build();  // Error al crear el alumno
	}


	// ----------------------------------------------------------------------------READ

	/// obtener todos los alumnos
	@GetMapping("/todousuarios")
	public ResponseEntity<?> obtenerUsuarios() {

		List<Usuario> listaUsuarios = repoUsuario.obtenerUsuarios();

		if (!listaUsuarios.isEmpty()) {
			return ResponseEntity.ok(listaUsuarios);

		} else {

			return ResponseEntity.notFound().build();
		}

	}

	// -----------------------------------------------------------------

	/// obtener todos los alumnos por un profesor
	@GetMapping("/obtener_alumnos_clase")

	public ResponseEntity<List<Usuario>> obtenerAlumnoOrdenadossDeClaseDeUnProfesor(
			@RequestParam String emailProfesor) {

		List<Usuario> alumnos = repoUsuario.obtenerAlumnosPorProfesor(emailProfesor);

		if (alumnos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Código 204 si no hay alumnos
		}
		return new ResponseEntity<>(alumnos, HttpStatus.OK); // Código 200 y la lista de alumnos en JSON
	}

	// -----------------------------------------------------------------
	/// obtener la letra de clase del profesor recibido
	  @PostMapping("/pedirLetraProfesor")
	    public ResponseEntity<String> obtenerLetraProfesor(@RequestBody String emailProfesor) {

	        if (emailProfesor == null || emailProfesor.isEmpty()) {
	            return ResponseEntity.badRequest().body("El email del profesor es obligatorio");
	        }

	        // Llamamos al servicio para obtener la clase del profesor
	        Optional<Clase> claseOpt = repoUsuario.obtenerClasePorEmail(emailProfesor);
	        
	        if (claseOpt.isPresent()) {
	            // Si el profesor tiene una clase asignada, devolvemos la letra de clase
	            Clase clase = claseOpt.get();
	            if (clase.getLetraClase() != null) {
	                return ResponseEntity.ok(clase.getLetraClase());
	            } else {
	                return ResponseEntity.notFound().build(); // Si la clase no tiene letra asignada
	            }
	        } else {
	            return ResponseEntity.notFound().build(); // Si no se encontró al profesor o no tiene clase asignada
	        }
	    }
//----------------------------------------------------------------------------DELETE POR ID

	/*
	 * ///eliminar un usuario por ID
	 * 
	 * @DeleteMapping("/borrar_usuario/{id}") public ResponseEntity<Usuario>
	 * eliminarUsuarioPorId(@PathVariable Long id){
	 * 
	 * /// NOS ASEGURAMOS DE QUE EXISTE PREVIAMENTE ESE USUARIO Optional<Usuario>
	 * alumnoParaBorrar=repoUsuario.borrarUsuarioPorId(id);
	 * 
	 * if(alumnoParaBorrar.isPresent()) {
	 * 
	 * /// SI EXISTE DEVOLVEMOS EN LA RESPUESTA EL ALUMNO ELIMINADO return
	 * ResponseEntity.ok(alumnoParaBorrar.get());
	 * 
	 * }else {
	 * 
	 * /// SI NO EXISTE DEVOLVEMOS EN LA RESPUESTA QUE NO EXISTE return
	 * ResponseEntity.notFound().build(); } }
	 */

	// ----------------------------------------------------------------- DELETE POR
	// MAIL
	/// eliminar un usuario por mail
	@DeleteMapping("/borrar_usuario_mail/{mailUsuario}")
	public ResponseEntity<Usuario> eliminarUsuarioPorMail(@PathVariable String mailUsuario) {

		/// NOS ASEGURAMOS DE QUE EXISTE PREVIAMENTE ESE USUARIO
		Optional<Usuario> alumnoParaBorrarMail = repoUsuario.borrarUsuarioPorMail(mailUsuario);

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
	public ResponseEntity<Usuario> modificamosDatosUser(@RequestBody Usuario alum, @PathVariable String mailUsuario) {
		// ACTUALIZAMOS LOS DATOS DEL USUARIO POR SU CORREO ELECTRÓNICO
		Optional<Usuario> alumnoParaModificar = repoUsuario.actualizarUsuarioPorMail(mailUsuario, alum);

		if (alumnoParaModificar.isPresent()) {
			// CONSTRUIMOS URI
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{mailUsuario}")
					.buildAndExpand(mailUsuario).toUri();

			// SI SE ACTUALIZÓ EL USUARIO, DEVOLVEMOS LA RESPUESTA CON LA UBICACIÓN DEL
			// RECURSO MODIFICADO
			return ResponseEntity.ok().location(location).build();
		} else {
			// SI NO SE ENCONTRÓ EL USUARIO, DEVOLVEMOS UNA RESPUESTA 404
			return ResponseEntity.notFound().build();
		}
	}

}// -----------FIN CONTROLADOR ControladorProfesor