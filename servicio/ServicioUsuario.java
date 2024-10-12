package es.daw.proyectoDAW.servicio;

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
    public Usuario findByMailAndPass(String mail, String pass) {
    	
        return repoUsuarios.findByMailAndPass(mail, pass);
    }
}