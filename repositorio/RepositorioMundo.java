package es.daw.proyectoDAW.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.daw.proyectoDAW.modelo.Mundo;
import es.daw.proyectoDAW.modelo.Usuario;

////****************************************************************************IMPORTS

public interface RepositorioMundo extends JpaRepository<Mundo, Long> {

	@Query("SELECT m FROM Mundo m WHERE m.idMundo = :id AND m.activadoMundo = TRUE")
	Mundo findMundoActivoById(@Param("id") Long id);

//*******************
	Mundo findByIdMundoAndActivadoMundo(Long id, Boolean activadoMundo);

//*****************
	@Query("SELECT m FROM Mundo m WHERE m.idMundo = :id")
	Mundo findMundoById(Long id);
//****************

	Mundo findFirstByActivadoMundoTrue();

//****************

	Mundo findFirstByActivadoMundoFalse();
	//****************

}//// FIN CLASE RepositorioMundo
