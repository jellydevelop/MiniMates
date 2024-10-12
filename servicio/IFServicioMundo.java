package es.daw.proyectoDAW.servicio;

import java.util.List;

import es.daw.proyectoDAW.modelo.Mundo;

////****************************************************************************IMPORTS

public interface IFServicioMundo {
	
	//-------------------------------------------------------------------------------------READ
	
	public List<Mundo>  obtenerMundos();
	
	public List<Mundo> obtenerAllPantallasOneMundo(Long idMundo) ;

		
	
	

}//-----------FIN  IFServicioMundo
