package es.daw.proyectoDAW.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.daw.proyectoDAW.modelo.Clase;
import es.daw.proyectoDAW.modelo.Usuario;

@Repository
public interface RepositorioClase extends JpaRepository<Clase, Long> {

	Clase findByLetraClase(String letraClase);

	// -------------------------------------------------------
	//devuelve id_Clase de la clase:
	// @Query("SELECT c.idClase FROM Clase c WHERE c.letraClase = :letraClase")
	  //  Long findIdClaseByLetra(@Param("letraClase") String letraClase);
	
    Long findIdClaseByLetraClase(String letraClase);

	// -------------------------------------------------------

	//Clase findClaseByLetraAndCentro(String letraClase, Centro_Educativo centro);
    @Query("SELECT u FROM Usuario u JOIN u.clase c WHERE u.rolUsuario = 'profesor' AND c.letraClase = :letraClase")
    Usuario findProfesorByLetraClase(@Param("letraClase") String letraClase);

	

}