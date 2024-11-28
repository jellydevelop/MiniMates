package es.daw.proyectoDAW.modelo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmailRequest {

    // Mail del usuario (alumno) que ha iniciado sesión
    @Email(message = "El formato del correo es inválido.")
    @NotEmpty(message = "El email del alumno no puede estar vacío.")
    private String emailAlumno;

    // Nombre del tutor que es el destinatario
    @NotEmpty(message = "El nombre del tutor no puede estar vacío.")
    private String nombreTutor;

    // Mensaje que se enviará
    @NotEmpty(message = "El cuerpo del mensaje no puede estar vacío.")
    private String cuerpoMensaje;

    // Letra de la clase para buscar al profesor
    @NotNull(message = "La letra de la clase no puede ser nula.")
    private String letraClase;

    // Constructor vacío (necesario para deserialización)
    public EmailRequest() {
    }

    // Constructor con parámetros
    public EmailRequest(String emailAlumno, String nombreTutor, String cuerpoMensaje, String letraClase) {
        this.emailAlumno = emailAlumno;
        this.nombreTutor = nombreTutor;
        this.cuerpoMensaje = cuerpoMensaje;
        this.letraClase = letraClase;
    }

    // Getters y Setters
    public String getEmailAlumno() {
        return emailAlumno;
    }

    public void setEmailAlumno(String emailAlumno) {
        this.emailAlumno = emailAlumno;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public String getCuerpoMensaje() {
        return cuerpoMensaje;
    }

    public void setCuerpoMensaje(String cuerpoMensaje) {
        this.cuerpoMensaje = cuerpoMensaje;
    }

    public String getLetraClase() {
        return letraClase;
    }

    public void setLetraClase(String letraClase) {
        this.letraClase = letraClase;
    }
}

