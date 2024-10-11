package es.daw.proyectoDAW.servicio;

import es.daw.proyectoDAW.modelo.Usuario;
///*****************************************************************************IMPORTS
public interface IFServicioUsuario {
	
	//-------------------------------------------------------------READ
    public Usuario findByMailAndPass(String mail, String pass);


}
