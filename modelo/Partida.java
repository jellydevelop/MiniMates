package es.daw.proyectoDAW.modelo;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

//************************************************IMPORTS
@Entity(name = "PARTIDA")
public class Partida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPartida;

	@Column(name = "FECHA_INICIO", nullable = false)
	private LocalDateTime fechaInicio;

	@Column(name = "INICIO_PARTIDA", nullable = false)
	private LocalDateTime inicioPartida;

	@Column(name = "DURACION_PARTIDA", nullable = false)
	private Integer duracionPartida = 15; // Duración máxima en minutos

	/// -->> RELACIONES
	@OneToMany(mappedBy = "partida")
	private List<Mundo> mundos;

	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) 
    private Usuario usuario;

	/// -->> CONSTRUCTOR VACÍO
	public Partida() {

	}
	/// -->> GETTERS

	 public Usuario getUsuario() {
	        return usuario;
	    }

	public Integer getDuracionPartida() {
		return duracionPartida;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public Long getIdPartida() {
		return idPartida;
	}

	public LocalDateTime getInicioPartida() {
		return inicioPartida;
	}

	public List<Mundo> getMundos() {
		return mundos;
	}
	/// -->> SETTERS

	public void setDuracionPartida(Integer duracionPartida) {
		this.duracionPartida = duracionPartida;
	}

	 public void setUsuario(Usuario usuario) { 
	        this.usuario = usuario;
	    }

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setIdPartida(Long idPartida) {
		this.idPartida = idPartida;
	}

	public void setInicioPartida(LocalDateTime inicioPartida) {
		this.inicioPartida = inicioPartida;
	}

	public void setMundos(List<Mundo> mundos) {
		this.mundos = mundos;
	}
	/// -->> MÉTODOS PROPIOS

	// Comprueba si han pasado los 15 minutos desde el inicio de la partida sino se bloquea y redirige a menú
	public boolean isPartidaActiva() {

		LocalDateTime tiempoLimite = fechaInicio.plusMinutes(duracionPartida);
		return LocalDateTime.now().isBefore(tiempoLimite);
	}

	// Comprueba si el mundo está dentro del límite de 7 días, sino se bloquea y
	// pasaría al siguiente
	public boolean isMundoActivo(Mundo mundo) {

		LocalDateTime limiteMundo = fechaInicio.plusDays(7);
		return LocalDateTime.now().isBefore(limiteMundo);
	}

}
