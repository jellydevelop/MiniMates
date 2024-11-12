package es.daw.proyectoDAW.repositorio;

import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.daw.proyectoDAW.modelo.Centro_Educativo;
import es.daw.proyectoDAW.modelo.Clase;

public interface RepositorioClase extends JpaRepository<Clase, Long> {

	Clase findByLetraClase(String letraClase);

	// -------------------------------------------------------
	//devuelve id_Clase de la clase:
	// @Query("SELECT c.idClase FROM Clase c WHERE c.letraClase = :letraClase")
	  //  Long findIdClaseByLetra(@Param("letraClase") String letraClase);
	
    Long findIdClaseByLetraClase(String letraClase);

	// -------------------------------------------------------

	//Clase findClaseByLetraAndCentro(String letraClase, Centro_Educativo centro);

	

}