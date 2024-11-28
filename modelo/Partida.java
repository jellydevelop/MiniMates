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
	
	@Column(name = "ESTADO", nullable = false)
    private String estado;

	/// -->> RELACIONES
	  @ManyToOne
	    @JoinColumn(name = "mundo_id")
	    private Mundo mundoActual;

	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) 
    private Usuario usuario;

	/// -->> CONSTRUCTOR VACÍO
	public Partida() {

	}
	/// -->> GETTERS

	public String getEstado() {
        return estado;

	}
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

	  public Mundo getMundoActual() {
	        return mundoActual;
	    }
	  
	/// -->> SETTERS

	  public void setEstado(String estado) {
		    this.estado = estado;  
		}
		
	public void setDuracionPartida(Integer duracionPartida) {
		this.duracionPartida = duracionPartida;
	}

	 public void setUsuario(Usuario usuarioId) { 
	        this.usuario = usuarioId;
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

	public void setMundoActual(Mundo mundoActual) {
        this.mundoActual = mundoActual;
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
