package es.daw.proyectoDAW.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
//------------------------------------
//------------------------------------

@Entity(name="PANTALLA")///GENERA LA TABLA PANTALLA
@Data
public class Pantalla {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ///GENERA ID AUTOMATICO

    private Long idPantalla;
    
	@Column(name="NOMBRE_PANTALLA",nullable=false, columnDefinition="varchar(15)")
		private String nombrePantalla;
	

///------GESTION DE TABLAS

    @ManyToOne
    @JoinColumn(name = "idMundo")
    	private Mundo mundo;

    @OneToMany(mappedBy = "idPantalla", cascade = CascadeType.ALL)
   
    	private List<Reto> retos;
    
////----CONSTR VACIO
	public Pantalla() {
		// TODO Auto-generated constructor stub
	}
	
////-----GETTER
	
	public Long getIdPantalla() {
		return idPantalla;
	}
	public Mundo getMundo() {
		return mundo;
	}
	public String getNombrePantalla() {
		return nombrePantalla;
	}
	public List<Reto> getRetos() {
		return retos;
	}
	
////-----SETTER
	public void setIdPantalla(Long idPantalla) {
		this.idPantalla = idPantalla;
	}
	public void setNombrePantalla(String nombrePantalla) {
		this.nombrePantalla = nombrePantalla;
	}
	public void setMundo(Mundo mundo) {
		this.mundo = mundo;
	}
	public void setRetos(List<Reto> retos) {
		this.retos = retos;
	}

}////---- FIN CLASE PANTALLA

