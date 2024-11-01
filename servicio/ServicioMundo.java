package es.daw.proyectoDAW.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.daw.proyectoDAW.modelo.Mundo;
import es.daw.proyectoDAW.repositorio.RepositorioJuego;
////****************************************************************************IMPORTS
@Service
public class ServicioMundo implements IFServicioMundo{
	
	@Autowired
		RepositorioJuego repoMundo;

	

	//-------------------------------------------------------------------------------------READ
	
	public List<Mundo>  obtenerMundos(){
		
		/////CASTEAMOS A UN ITERABLE
			return repoMundo.findAll();
		
	}

	///----------------------------------------------------



	public List<Mundo> obtenerAllPantallasOneMundo(Long idMundo) {

		return repoMundo.findAll();

	}
	

	



//-----------------------------------------------------------------------------------------CREATE
	
	

//--------------------------------------------------------------------------------------------UPDATE

	
	
	
//--------------------------------------------------------------------------------------------DELETE




}//-----------FIN  ServicioMundo
