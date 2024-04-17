package es.daw.proyectoDAW.repositorio;

import org.springframework.data.repository.CrudRepository;

import es.daw.proyectoDAW.modelo.Usuario;

//--------------------------------------------------------
////------------CLASE QUE USA METODOS CRUD POR SER HIJA DE CRUDREPOSITORY


public interface RepositorioUsuarios extends CrudRepository<Usuario, Long> {
	
	/// CONSULTA PARA BUSCAR POR NOMBRE DE USUARIO
   public  Usuario findByNombreUsuario(String nombreUsuario);



}//// FIN CLASE RepositorioUsuarios