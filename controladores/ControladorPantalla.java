package es.daw.proyectoDAW.controladores;

import es.daw.proyectoDAW.modelo.Mundo;
import es.daw.proyectoDAW.modelo.Pantalla;
import es.daw.proyectoDAW.servicio.ServicioMundo;
import es.daw.proyectoDAW.servicio.ServicioPantalla;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class ControladorPantalla {

    @Autowired
    private ServicioPantalla serviPantalla;
    
    @Autowired
    private ServicioMundo serviMundo;
//*******************************************************
    @PostMapping("salvarPantalla")
    public ResponseEntity<String> guardarPantalla(@RequestBody Pantalla pantalla) {
        
        // Verificar que el 'mundo_id' está presente en el cuerpo del JSON
        Long mundoId = pantalla.getMundo().getIdMundo();  // Obtenemos el ID del mundo del JSON
        if (mundoId == null) {
            return ResponseEntity.badRequest().body("mundo_id no proporcionado");
        }

        // Recuperamos la entidad 'Mundo' usando el ID proporcionado en el JSON
        Mundo mundo = serviMundo.obtenerMundo(mundoId);
        if (mundo == null) {
            return ResponseEntity.badRequest().body("Mundo no encontrado");
        }

        // Asociamos el 'Mundo' a la 'Pantalla'
        pantalla.setMundo(mundo);

        // Guardamos la entidad 'Pantalla' con el 'Mundo' asociado
        serviPantalla.guardarPantalla(pantalla);

        return ResponseEntity.ok("Datos guardados correctamente");
    }
}