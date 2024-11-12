package es.daw.proyectoDAW.errores;

public class ClaseNoEncontradaException extends RuntimeException {
    public ClaseNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
