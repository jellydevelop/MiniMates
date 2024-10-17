package es.daw.proyectoDAW.servicio;
//------------------------------------
//---------CLASE QUE CREA LAS CABECERAS PARA LOS METODOS DEL SERVICIO

import java.util.List;
import java.util.Optional;

import es.daw.proyectoDAW.modelo.Usuario;

public interface IFServicioAlumno {
	

	//------------------------------------------------------------------------------READ
	
	 public Usuario obtenerProfesorPorLetra(String letraClase);
	 

}