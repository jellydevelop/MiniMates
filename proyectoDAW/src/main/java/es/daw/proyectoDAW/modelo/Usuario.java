package es.daw.proyectoDAW.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

//------------------------------------
//------------------------------------

@Entity(name="USUARIOS")///GENERA LA TABLA USUARIOS
@Data
public class Usuario {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ///GENERA ID AUTOMATICO
    
    ///ATRIBUTOS PARA LOGIN
    
    private Long idUsuario;
    
	@Column(name="NOMBRE_USUARIO",nullable=false, columnDefinition="varchar(10)")
    private String nombre;
	
	@Column(name="MAIL_USUARIO",nullable=false, columnDefinition="varchar(25)")
    private String mailUsuario;
    
    
    
 ///------GESTION DE TABLAS
    
    @ManyToMany
    @JoinTable(name = "usuario_reto",
               joinColumns = @JoinColumn(name = "ID_USUARIO"),
               inverseJoinColumns = @JoinColumn(name = "ID_RETO"))
    
    private List<Reto> retos;
    

////----CONSTR VACIO
    public Usuario() {
		
	}
    
////-----GETTER
    
    public Long getId() {
		return idUsuario;
	}
    public String getNombre() {
		return nombre;
	}
    public String getMail() {
		return mailUsuario;
	}public List<Reto> getRetos() {
		return retos;
	}
	
	
////-----SETTER
	
	public void setId(Long id) {
		this.idUsuario = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setMail(String mail) {
		this.mailUsuario = mail;
	}
	public void setRetos(List<Reto> retos) {
		this.retos = retos;
	}
	
	

}////---- FIN CLASE USUARIO

