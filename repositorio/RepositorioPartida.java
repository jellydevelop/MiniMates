package es.daw.proyectoDAW.repositorio;

import es.daw.proyectoDAW.modelo.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPartida extends JpaRepository<Partida, Long> {

	Partida findFirstByUsuario_IdUsuarioAndEstado(Long idUsuario, String estado);

	
	Partida findByIdPartida(Long idPartida);


//	void deleteByUsuarioId(Long idUsuario);

    void deleteByUsuario_IdUsuario(Long idUsuario);


	//****************************
	

}
