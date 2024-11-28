package es.daw.proyectoDAW.modelo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//************************************************IMPORTS
@Entity(name = "CENTRO_EDUCATIVO")
public class Centro_Educativo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCentro;

	@Column(name = "NOMBRE_CENTRO", columnDefinition = "varchar(30)", nullable = false)
	private String nombreCentro;

	@Column(name = "DIRECCION_CENTRO", columnDefinition = "varchar(50)", nullable = false)
	private String direccionCentro;

///-->> RELACIONES
	@OneToMany(mappedBy = "centro")
	@JsonIgnore
	private List<Clase> clases;

	

/// -->> CONSTRUCTOR VACÍO
	public Centro_Educativo() {
	    this.clases = new ArrayList<>();
	}
	/// -->> CONSTRUCTOR con ID

	public Centro_Educativo(Long id) {
	    this.idCentro = id;
	    this.clases = new ArrayList<>();
	}
	
	
///-->> GETTERS
	public Long getId() {
		return idCentro;
	}

	public String getDireccionCentro() {
		return direccionCentro;
	}

	public String getNombreCentro() {
		return nombreCentro;
	}

///-->> SETTERS

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	public void setDireccionCentro(String direccionCentro) {
		this.direccionCentro = direccionCentro;
	}

	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}

	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}
	
	

}
