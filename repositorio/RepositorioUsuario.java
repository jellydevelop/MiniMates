package es.daw.proyectoDAW.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.daw.proyectoDAW.modelo.Usuario;

//********************************************************************************IMPORTS


public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
    
    @Query("SELECT u FROM USUARIO u WHERE u.mailUsuario = :mail AND u.passUsuario = :pass")
    	Usuario findByMailAndPass(@Param("mail") String mail, @Param("pass") String pass);

    	Usuario findByMailUsuario(String emailUsuario);

    	Optional<Usuario> findOptionalByMailUsuario(String mailUsuario);

    	List<Usuario> findByRolUsuario(String rolUsuario);
    			
	    List<Usuario> findByLetraClaseAndRolUsuario(String letraClase, String rolUsuario);

    	
    	

}//// FIN CLASE RepositorioUsuarios