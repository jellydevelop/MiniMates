package es.daw.proyectoDAW.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.repositorio.RepositorioUsuario;
//*****************************************************************IMPORTS


@Service
public class ServicioUsuario implements IFServicioUsuario{

    @Autowired
    private RepositorioUsuario repoUsuarios;

    //---------------------------------------------------------------------READ
    
    ///*******para buscar  email + password ->> para verificar login
    public Usuario findByMailAndPass(String mail, String pass) {
    	
        return repoUsuarios.findByMailAndPass(mail, pass);
    }
    ///*******para buscar solo email ->> para mandar correos
	public Usuario findByMailUsuario(String emailUsuario) {
		return repoUsuarios.findByMailUsuario(emailUsuario);
	}
	
   
	
	
	
	

}