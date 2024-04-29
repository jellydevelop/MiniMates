package es.daw.proyectoDAW.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import es.daw.proyectoDAW.modelo.Usuario;

//--------------------------------------------------------
////------------CLASE QUE USA METODOS CRUD POR SER HIJA DE CRUDREPOSITORY


public interface RepositorioUsuarios extends CrudRepository<Usuario, Long> {
	
	/// CONSULTA PARA BUSCAR POR MAIL DE USUARIO
  // public  Usuario findByMailUsuario(String mailUsuario);
	@Query("SELECT u FROM USUARIO u WHERE u.mailUsuario = :mail AND u.passUsuario = :pass")
			public Usuario findByMailAndPass(@Param("mail") String mail, @Param("pass") String pass);
	 //---------------------------------------------------------------------
  ///METODO PARA ENCONTRAR UN USUARIO POR SU EMAIL. USAMOS EL ATRIBUTO DE USUARIO "MAILUSUARIO"
	Usuario findByMailUsuario(String mailUsuario);

	



}//// FIN CLASE RepositorioUsuarios