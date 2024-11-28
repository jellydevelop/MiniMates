package es.daw.proyectoDAW.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.repositorio.RepositorioClase;
import es.daw.proyectoDAW.repositorio.RepositorioUsuario;

////****************************************************************************IMPORTS

@Service
public class ServicioAlumno implements IFServicioAlumno {

	@Autowired
	RepositorioUsuario repoUsuarios;
	
	@Autowired
	RepositorioClase repoClase;

	// -------------------------------------------------------------------------------------READ

	// ****************************************************************

	//public Usuario obtenerProfesorPorLetra(String letraClase) {
	//	return repoUsuarios.findProfesorByLetraClase(letraClase);
	//}

	// ****************************************************************
	public Usuario findByMailUsuario(String emailUsuario) {
		return repoUsuarios.findByMailUsuario(emailUsuario);
	}
//*******************
	public String obtenerInicialesPorEmail(String emailUsuario) {
		Usuario alumMail=repoUsuarios.findByMailUsuario(emailUsuario);
		
		 // Verificar si el alumno existe
		if (alumMail == null) {
	        System.out.println("Alumno no encontrado para el email: " + emailUsuario);
	        return null;
	    }
		
		 if (!alumMail.esAlumno()) {
		        System.out.println("El usuario no es alumno: " + emailUsuario);
		        return null;
		    }
        
        
			  // Obtener los datos del alumno: nombre, primer apellido y segundo apellido
	        String nombre = alumMail.getNombreUsuario();
	        String primerApellido = alumMail.getPrimApellidoUsuario();
	        String segundoApellido = alumMail.getSecApellidoUsuario();
	        System.out.println("Nombre: " + nombre + ", Primer Apellido: " + primerApellido + ", Segundo Apellido: " + segundoApellido);

	        
	        
	        // Generar las iniciales
	        String iniciales = "";
	        
	        if (nombre != null && !nombre.isEmpty()) {
	            iniciales += nombre.charAt(0); // Inicial del nombre
	        }
	        if (primerApellido != null && !primerApellido.isEmpty()) {
	            iniciales += primerApellido.charAt(0); // Inicial del primer apellido
	        }
	        if (segundoApellido != null && !segundoApellido.isEmpty()) {
	            iniciales += segundoApellido.charAt(0); // Inicial del segundo apellido
	        }
	        System.out.println("Iniciales generadas: " + iniciales);

	        return iniciales.toUpperCase(); // Devolver las iniciales en mayúsculas
	    }
	

}///// FIN CLASE ServicioAlumno
