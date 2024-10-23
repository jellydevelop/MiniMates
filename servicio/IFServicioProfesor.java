package es.daw.proyectoDAW.servicio;
//------------------------------------
//---------CLASE QUE CREA LAS CABECERAS PARA LOS METODOS DEL SERVICIO

import java.util.List;
import java.util.Optional;

import es.daw.proyectoDAW.modelo.Usuario;

public interface IFServicioProfesor {

	//------------------------------------------------------------------------------READ
	
	
	/////CON ESTE METODO OBTENEMOS UNA LISTA CON TODOS LOS USUARIOS
		public List<Usuario>  obtenerUsuarios();
		
	/////CON ESTE METODO OBTENEMOS EL ROL DE UN USUARIO CON SU ID
		public String obtenerRolUsuario(Long idUsuario);
		
	/////CON ESTE METODO VALIDAMOS EN BDD PASSOWRD + MAIL
		public Optional<Usuario> verificarExisteUsuario(String correo, String password) ;
		
	/////CON ESTE METODO OBTENEMOS LA EXISTENCIA DE UN USUARIO CON SU MAIL
		public Optional<Usuario> findByMail(String correo);	
		
	/////
		public boolean checkPassword(Usuario usuario, String password);
		
	//// CON ESTE METODO OBTENEMOS LOS USUARIOS QUE TIENEN X ROL
		public Optional<List<Usuario>> obtenerUsuariosPorRol(String rol);
		
		public Usuario findByMailAndPass(String mail, String pass);
		
		 public List<Usuario> obtenerAlumnosPorLetra(String letraClase);

	//------------------------------------------------------------------------------CREATE
		
	/////CON ESTE METODO AÑADIMOS UN  USUARIO /// SIN PROFESOR ASOCIADO
		public Usuario aniadeUsuarioAlumno(Usuario alum) ;
	//---------------------------------------------------------------------------------UPDATE
		
	/////CON ESTE METODO MODICIFICAMOS DATOS DE UN USUARIO
		public  Usuario actualizarUsuarioPorId(Usuario alumProf);
		
	//--------------------------------------------------------------------------------DELETE
		
	/////CON ESTE METODO ELIMINAMOS USUARIO POR ID
	//	Optional<Usuario> borrarUsuarioPorId(Long id);
		
	/////CON ESTE METODO ELIMINAMOS USUARIO POR MAIL
		 Optional<Usuario> borrarUsuarioPorMail(String mailUsuario);




	
	
	
}///// FIN CLASE IFServicioUsuario


