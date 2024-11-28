package es.daw.proyectoDAW.servicio;

import es.daw.proyectoDAW.modelo.Pantalla;
import es.daw.proyectoDAW.repositorio.RepositorioPantalla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPantalla {

    @Autowired
    private RepositorioPantalla pantallaRepo;

    public Pantalla guardarPantalla(Pantalla pantalla) {
        return pantallaRepo.save(pantalla);
    }

}
