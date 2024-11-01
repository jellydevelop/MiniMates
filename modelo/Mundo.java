package es.daw.proyectoDAW.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

//************************************

@Entity(name = "MUNDO")
@Data
public class Mundo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMundo;

	@Column(name = "NOMBRE_MUNDO", nullable = false, columnDefinition = "varchar(10)")
	private String nombreMundo;

	@Column(name = "ACTIVADO", nullable = false, columnDefinition = "char(1)")
	private Boolean activadoMundo;

	/// ------ RELACIONES

	@ManyToOne
	@JoinColumn(name = "partida_id")
	private Partida partida;

	@OneToMany(mappedBy = "mundo")
	private List<Pantalla> pantallas;

////----CONSTR VACIO

	public Mundo() {
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
	public boolean isHabilitado() {
		return this.activadoMundo;
	}

	// cuenta el número de retos en el mundo
	public int contarRetos() {
		return (pantallas != null) ? pantallas.size() : 0;
	}

	// obtiene una lista de tipos de reto en el mundo
	public List<String> obtenerTiposRetos() {
		return pantallas.stream().map(Pantalla::getTipoReto).distinct().toList();
	}
}//// ---- FIN CLASE MUNDO
