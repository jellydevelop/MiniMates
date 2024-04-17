package es.daw.proyectoDAW.modelo;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

//-----------------------------------
//-----------------------------------


@Entity(name="RETO")///GENERA LA TABLA RETO
@Data
public class Reto {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ///GENERA ID AUTOMATICO

    private Long idReto;
    
    @Column(name="TIPO_RETO",nullable=false, columnDefinition="varchar(15)")
    private String tipoReto;
    
    @Column(name="TIEMPO_RETO",nullable=false, columnDefinition="DateTime")
    private Date tiempoReto;
    
    @Column(name="CONSEGUIDO_RETO",nullable=false, columnDefinition="char(1)")
    private Boolean okReto;
    
    @Column(name="FALLADO_RETO",nullable=false, columnDefinition="char(1)")
    private Boolean failReto;
    
 
    @Column(name="HABILITADO_RETO",nullable=false, columnDefinition="char(1)")
    private Boolean habilitadoReto;

 ///------GESTION DE TABLAS

    @ManyToOne
    @JoinColumn(name = "idPantalla")
    
   		private Pantalla pantalla;

    @ManyToMany(mappedBy = "idReto")
    	private List<Usuario> usuarios;

////----CONSTR VACIO
	public Reto() {
		// TODO Auto-generated constructor stub
	}
	
////-----GETTER
	
	public Long getIdReto() {
		return idReto;
	}
	public String getTipoReto() {
		return tipoReto;
	}
	public Date getTiempoReto() {
		return tiempoReto;
	}
	public Boolean getOkReto() {
		return okReto;
	}
	public Boolean getFailReto() {
		return failReto;
	}
	public Boolean getHabilitadoReto() {
		return habilitadoReto;
	}
	public Pantalla getPantalla() {
		return pantalla;
		/// PANTALLA A LA QUE PERTENECE ESTE RETO
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
		///USUARIOS QUE HAN JUGADO ESTE RETO
	}
	
////-----SETTER
	
	public void setIdReto(Long idReto) {
		this.idReto = idReto;
	}
	public void setTipoReto(String tipoReto) {
		this.tipoReto = tipoReto;
	}
	public void setTiempoReto(Date tiempoReto) {
		this.tiempoReto = tiempoReto;
	}
	public void setOkReto(Boolean okReto) {
		this.okReto = okReto;
	}
	public void setFailReto(Boolean failReto) {
		this.failReto = failReto;
	}
	public void setHabilitadoReto(Boolean habilitadoReto) {
		this.habilitadoReto = habilitadoReto;
	}
	public void setPantalla(Pantalla pantalla) {
		this.pantalla = pantalla;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}////---- FIN CLASE RETO