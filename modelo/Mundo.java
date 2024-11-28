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
import jakarta.persistence.Table;
import lombok.Data;

//************************************

@Entity
@Table(name = "MUNDO")
@Data
public class Mundo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mundo")
	private Long idMundo;

	@Column(name = "NOMBRE_MUNDO", nullable = false, columnDefinition = "varchar(20)")
	private String nombreMundo;

	@Column(name = "ACTIVADO",columnDefinition = "BOOLEAN",nullable = false)
	private Boolean activadoMundo;
	
	@Column(name = "FECHA_INICIO_MUNDO", nullable = false, columnDefinition = "DATETIME(6)")
	private LocalDateTime fechaInicioMundo;

	/// ------ RELACIONES

	@ManyToOne
	@JoinColumn(name = "partida_id")
	private Partida partida;

	@OneToMany(mappedBy = "mundo")
	private List<Pantalla> pantallas;

////----CONSTR VACIO

	public Mundo() {
	    this.activadoMundo = false; // Por defecto, desactivado
	}


////-----GETTER

	public List<Pantalla> getPantallas() {
		return pantallas;
	}

	public Boolean getActivadoMundo() {
		return activadoMundo;
	}

	public Long getIdMundo() {
		return idMundo;
	}

	public String getNombreMundo() {
		return nombreMundo;
	}

	public Partida getPartida() {
		return partida;
	}
	public LocalDateTime getFechaInicioMundo() {
		return fechaInicioMundo;
	}

////-----SETTER

	public void setActivadoMundo(Boolean activadoMundo) {
		this.activadoMundo = activadoMundo;
	}

	public void setIdMundo(Long idMundo) {
		this.idMundo = idMundo;
	}

	public void setNombreMundo(String nombreMundo) {
		this.nombreMundo = nombreMundo;
	}

	public void setPantallas(List<Pantalla> pantallas) {
		this.pantallas = pantallas;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	
	public void setFechaInicioMundo(LocalDateTime fechaInicioMundo) {
		this.fechaInicioMundo = fechaInicioMundo;
	}

////-----MÉTODOS PROPIOS

	// habilita el mundo
	public void habilitar() {
		this.activadoMundo = true;
	}

	// deshabilita el mundo
	public void deshabilitar() {
		this.activadoMundo = false;
	}

	// verifica si el mundo está habilitado
	public boolean isActivado() {
	    return Boolean.TRUE.equals(this.activadoMundo);
	}


	// cuenta el número de retos en el mundo
	public int contarRetos() {
		return (pantallas != null) ? pantallas.size() : 0;
	}

	// obtiene una lista de tipos de reto en el mundo
	public List<String> obtenerTiposRetos() {
		return pantallas.stream().map(Pantalla::getTipoReto).distinct().toList();
	}
	
	//para controlar SI el mundo ha sido jugado mas de 7 dias
	public boolean isExpirado() {
	    return LocalDateTime.now().isAfter(this.fechaInicioMundo.plusDays(7));
	}
	
	public void setEstado(String estado) {
	    this.activadoMundo = "1".equals(estado);//1 es true, 0 false
	}


	
	
}//// ---- FIN CLASE MUNDO
