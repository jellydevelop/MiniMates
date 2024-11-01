package es.daw.proyectoDAW.modelo;

public class EmailRequest {

	// Token del usuario (alumno) que ha iniciado sesión
	private String token;
	
	// Nombre del tutor que es el destinatario
	private String nombreTutor;

	// Mensaje que se enviará
	private String cuerpoMensaje;

	// --> Getters 
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNombreTutor() {
		return nombreTutor;
	}
	// -->  Setters
	public void setNombreTutor(String nombreTutor) {
		this.nombreTutor = nombreTutor;
	}

	public String getCuerpoMensaje() {
		return cuerpoMensaje;
	}

	public void setCuerpoMensaje(String cuerpoMensaje) {
		this.cuerpoMensaje = cuerpoMensaje;
	}
}
