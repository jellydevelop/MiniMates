package es.daw.proyectoDAW.modelo;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

//------------------------------------
//------------------------------------
@Data
@Entity(name="USUARIOS")///GENERA LA TABLA USUARIOS
public class Usuario {
	
	@Id@GeneratedValue
	private Long id;
    
	@Column(name="NOMBRE_USUARIO",nullable=false, columnDefinition="varchar(10)")
    private String nombreUsuario;
	
	@Column(name="MAIL_USUARIO",nullable=false, columnDefinition="varchar(25)")
    private String mailUsuario;
	
	@Column(name="ROL_USUARIO",nullable=false, columnDefinition="varchar(8)")
    private String rolUsuario;
    
    
    
 ///------GESTION DE TABLAS
    
    /*@ManyToMany
    @JoinTable(name = "usuario_reto",
               joinColumns = @JoinColumn(name = "ID_USUARIO"),
               inverseJoinColumns = @JoinColumn(name = "ID_RETO"))
    
    private List<Reto> retos;
    */

////----CONSTR VACIO
    public Usuario() {
		
	}
    
////-----GETTER
    
    public Long getId() {
		return id;
	}
    public String getNombre() {
		return nombreUsuario;
	}
    public String getMail() {
		return mailUsuario;
    }
    public String getRolUsuario() {
		return rolUsuario;
	}
	/*}public List<Reto> getRetos() {
		return retos;
	}*/
	
	
////-----SETTER
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombreUsuario = nombre;
	}
	public void setMail(String mail) {
		this.mailUsuario = mail;
	}
	public void setRolUsuario(String rolUsuario) {
		this.rolUsuario = rolUsuario;
	}
	/*public void setRetos(List<Reto> retos) {
		this.retos = retos;
	}*/
	
	

}////---- FIN CLASE USUARIO

