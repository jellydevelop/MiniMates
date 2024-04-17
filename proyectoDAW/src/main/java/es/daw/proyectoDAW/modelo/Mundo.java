package es.daw.proyectoDAW.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Data;

//------------------------------------
//------------------------------------


@Entity(name="MUNDO")///GENERA LA TABLA MUNDO
@Data
public class Mundo {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ///GENERA ID AUTOMATICO

    private Long idMundo;
    
	@Column(name="NOMBRE_MUNDO",nullable=false, columnDefinition="varchar(7)")
    private String nombreMundo;
	
	@Column(name="CANT_PANTALLAS_MUNDO",nullable=false, columnDefinition="char(2)")
	private String cantidadPantallasMundo;
    

 ///------GESTION DE TABLAS

	    @OneToMany(mappedBy = "idMundo", cascade = CascadeType.ALL)//TODO LO QUE SE EJECUTE SERÁ EN CASCADA
	    
	    private List<Pantalla> pantallas;
	    
////----CONSTR VACIO
	    
	    public Mundo() {
			// TODO Auto-generated constructor stub
		}
	    
	    
////-----GETTER
	    
	    public Long getId() {
			return idMundo;
		}
	    public String getNombre() {
			return nombreMundo;
		}
	    public List<Pantalla> getPantallas() {
			return pantallas;
		}	  
	    public String cantidadPantallasMundo() {
			return cantidadPantallasMundo;
		}
	    
    
////-----SETTER
    
		public void setId(Long id) {
			this.idMundo = id;
		}  
		public void setNombre(String nombre) {
			this.nombreMundo = nombre;
			
		}
		public void setPantallas(List<Pantalla> pantallas) {
			this.pantallas = pantallas;
		}
		public void setCantidadPantallas(String cantidadPantallas) {
			this.cantidadPantallasMundo = cantidadPantallas;
		}
    

}////---- FIN CLASE MUNDO