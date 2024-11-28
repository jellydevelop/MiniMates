package es.daw.proyectoDAW.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.daw.proyectoDAW.modelo.Clase;
import es.daw.proyectoDAW.modelo.Usuario;

//********************************************************************************IMPORTS

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

    // MÉTODOS GENERALES
    List<Usuario> findByRolUsuario(String rolUsuario);

    /////////////// VALIDACIÓN LOGIN /////////////-----------------
    @Query("SELECT u FROM Usuario u WHERE u.mailUsuario = :mail AND u.passUsuario = :pass")
    Usuario findByMailAndPass(@Param("mail") String mail, @Param("pass") String pass);

    Optional<Usuario> findOptionalByMailUsuario(String mailUsuario);

    Usuario findByMailUsuario(String mailUsuario);

    /////////////// PERFIL PROFESOR /////////////-----------------

    // Lista de alumnos en una clase específica
    @Query("SELECT u FROM Usuario u WHERE u.clase.letraClase = :letraClase AND u.rolUsuario = 'alumno'")
    List<Usuario> obtenerAlumnosPorLetra(@Param("letraClase") String letraClase);

    // Lista de usuarios con rol "PROFESOR"
    @Query("SELECT u FROM Usuario u WHERE u.rolUsuario = ?1")
    List<Usuario> findByProfesor(String rolUsuario);
    
    @Query("SELECT c FROM Clase c WHERE c.letraClase = :letraClase")
    Clase findByLetraClase(@Param("letraClase") String letraClase);

    // Buscar usuario por NIA
    @Query("SELECT u FROM Usuario u WHERE u.niaAlumno = :nia")
    Optional<Usuario> findByNia(String nia);	
    
    
	  void deleteById(Long id);

    /////////////// PERFIL ALUMNO /////////////-----------------

    // Contacto: Busca el profesor de una clase específica
   // @Query("SELECT u FROM Usuario u WHERE u.clase.letraClase = :letraClase AND u.rolUsuario = 'PROFESOR'")
   // Usuario findProfesorByLetraClase(@Param("letraClase") String letraClase);

    // Todos los profesores
    @Query("SELECT u FROM Usuario u WHERE u.rolUsuario = 'PROFESOR'")
    List<Usuario> findProfesores();

    
    //Clase findClaseConProfesor(@Param("letraClase") String letraClase);

    /////////////// REPO CLASE /////////////-----------------

    // Encuentra usuarios por letra de clase
    List<Usuario> findByClase_LetraClase(String letraClase);
    

    // Encuentra usuarios por letra de clase y rol
    List<Usuario> findByClase_LetraClaseAndRolUsuario(String letraClase, String rolUsuario);

    // Obtén la letra de la clase por correo electrónico del usuario
    @Query("SELECT u.clase.letraClase FROM Usuario u WHERE u.mailUsuario = :email")
    Optional<Clase> obtenerLetraClasePorEmail(@Param("email") String email);

    @Query("select u from Usuario u where u.rolUsuario = :rolUsuario and u.clase.letraClase = :letraClase")
    Usuario findByRolUsuarioAndLetraClase(@Param("rolUsuario") String rolUsuario, 
                                                @Param("letraClase") String letraClase);

	Optional<Usuario> findByNiaAlumno(String niaAlum);

	

}
