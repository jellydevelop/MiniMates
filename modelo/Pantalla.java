package es.daw.proyectoDAW.modelo;

import java.time.Instant;
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

@Entity
public class Pantalla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "TIPO_RETO", columnDefinition = "varchar(30)", nullable = false)
    private String tipoReto;
	
	@Column(name = "DURACION_PANTALLA", nullable = false)
    private Instant duracionPantalla;
	
	
	@Column(name = "ACIERTOS_PANTALLA",nullable = false)
    private int aciertosPantalla;
	
	@Column(name = "FALLOS_PANTALLA", nullable = false)
    private int fallosPantalla;

	///-->> RELACIONES
    @ManyToOne
    @JoinColumn(name = "mundo_id")
    private Mundo mundo;

  /// -->> CONSTRUCTOR VACÍO
public Pantalla() {
}

/// -->> CONSTRUCTOR COMPLETLO
public Pantalla(String tipoReto, Instant duracionPantalla, int aciertosPantalla, int fallosPantalla, Mundo mundo) {
    this.tipoReto = tipoReto;
    this.duracionPantalla = duracionPantalla;
    this.aciertosPantalla = aciertosPantalla;
    this.fallosPantalla = fallosPantalla;
    this.mundo = mundo;  
}
/// -->> GETTERS
public int getAciertosPantalla() {
	return aciertosPantalla;
}
public int getFallosPantalla() {
	return fallosPantalla;
}

public Long getId() {
	return id;
}
public Mundo getMundo() {
	return mundo;
}

public Instant getDuracionPantalla() {
	return duracionPantalla;
}
public String getTipoReto() {
	return tipoReto;
}
/// -->> SETTERS
public void setAciertosPantalla(int aciertosPantalla) {
	this.aciertosPantalla = aciertosPantalla;
}
public void setFallosPantalla(int fallosPantalla) {
	this.fallosPantalla = fallosPantalla;
}
public void setMundo(Mundo mundo) {
	this.mundo = mundo;
}
public void setId(Long id) {
	this.id = id;
}
public void setDuracionPantalla(Instant duracionPantalla) {
	this.duracionPantalla = duracionPantalla;
}
public void setTipoReto(String tipoReto) {
	this.tipoReto = tipoReto;
}

/// -->> MÉTODOS PROPIOS

public int getTotalRespuestas() {
    return aciertosPantalla + fallosPantalla;
}



}

