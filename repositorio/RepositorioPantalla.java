package es.daw.proyectoDAW.repositorio;

import es.daw.proyectoDAW.modelo.Pantalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPantalla extends JpaRepository<Pantalla, Long> {
}