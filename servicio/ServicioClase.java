package es.daw.proyectoDAW.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.daw.proyectoDAW.modelo.Clase;
import es.daw.proyectoDAW.repositorio.RepositorioClase;

//**************************************************
 


@Service
public class ServicioClase implements IFServicioClase{
@Autowired
RepositorioClase repoClase;

//*********
	public Clase obtenerClasePorLetra(String letraClase) {
        return repoClase.findByLetraClase(letraClase);
	}

}
