package es.daw.proyectoDAW.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.daw.proyectoDAW.modelo.Clase;
import es.daw.proyectoDAW.modelo.Usuario;

//********************************************************************************IMPORTS


public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
    

    	List<Usuario> findByRolUsuario(String rolUsuario);
    			

/////////////// VALIDACIÓN LOGIN /////////////-----------------   	
    	
    	//--> login.html
    	 @Query("SELECT u FROM Usuario u WHERE u.mailUsuario = :mail AND u.passUsuario = :pass")
    	    Usuario findByMailAndPass(@Param("mail") String mail, @Param("pass") String pass);
    	    
    	    Optional<Usuario> findOptionalByMailUsuario(String mailUsuario);
    	    
    	    Usuario findByMailUsuario(String mailUsuario);
		
/////////////// PERFIL PROFESOR   /////////////----------------- 	

    	//--> listaAalumnos.html
    	    @Query("SELECT u FROM Usuario u WHERE u.clase.letraClase = :letraClase AND u.rolUsuario = :rolUsuario")
    	    List<Usuario> obtenerAlumnosPorLetra(@Param("letraClase") String letraClase, @Param("rolUsuario") String rolUsuario);
    	    
    	    @Query("SELECT u FROM Usuario u WHERE u.rolUsuario = ?1")
    	    List<Usuario> findByProfesor(String rolUsuario);
    	    
    	 // Busca alumnos por letra de clase basada en el mail del profesor
    	    @Query("SELECT u FROM Usuario u WHERE u.clase.letraClase = (SELECT p.clase.letraClase FROM Usuario p WHERE p.mailUsuario = :mailProfesor) AND u.rolUsuario = 'alumno'")
    	    List<Usuario> findAlumnosPorLetraClaseDeProfesor(@Param("mailProfesor") String mailProfesor);
    	   
    	 // busca la letra de clase del profesor según su correo
    	    @Query("SELECT u.claseComoProfesor FROM Usuario u WHERE u.mailUsuario = :mailUsuario")
    	    Optional<Clase> obtenerClasePorEmail(@Param("mailUsuario") String mailUsuario);



		
/////////////// PERFIL ALUMNO /////////////-----------------
		
    	//--> contacto.html
    	    @Query("SELECT u FROM Usuario u WHERE u.clase.letraClase = ?1 AND u.rolUsuario = 'profesor'")
    	    Usuario findByLetraClaseAndRolUsuarioProfesor(String letraClase);
    	 	
/////////////// REPO CLASE /////////////-----------------


    	    Optional<Usuario> findByClase_LetraClase(String letraClase);

    	    @Query("SELECT u FROM Usuario u WHERE u.clase.letraClase = :letraClase")
    	    Optional<Usuario> findByLetraClase(@Param("letraClase") String letraClase);

    	    @Query("SELECT c FROM CLASE c WHERE c.letraClase = :letraClase")
    	    Optional<Clase> findClaseByLetraClase(@Param("letraClase") String letraClase);

    	    // Método para buscar usuarios por letra de clase y rol "alumno"
    	    List<Usuario> findByClase_LetraClaseAndRolUsuario(String letraClase, String rolUsuario);



}//// FIN CLASE RepositorioUsuarios