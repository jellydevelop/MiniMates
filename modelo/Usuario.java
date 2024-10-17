package es.daw.proyectoDAW.modelo;

import java.util.List;

import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Entity(name="USUARIO")
@NamedQuery(name = "USUARIO.findByEmailAddress",
query = "select u from USUARIO u where u.mailUsuario = ?1")///GENERA LA TABLA USUARIOS
public class Usuario {
	
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	@Column(name="NOMBRE_USUARIO",columnDefinition="varchar(10)")
    private String nombreUsuario;
	
	@Column(name="MAIL_USUARIO",columnDefinition="varchar(25)")
    private String mailUsuario;
	
	
	@Column(name="PASS_USUARIO",columnDefinition="varchar(10)")
	
	private String passUsuario;
	
	
	@Column(name="ROL_USUARIO",columnDefinition="varchar(8)")
    private String rolUsuario;
	
	@Column(name="LETRA_CLASE", columnDefinition="varchar(2)")
    private String letraClase;
    
    
    
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
////----CONSTR PARA DAR DE ALTA

    public Usuario(String nombreUsuario, String mailUsuario, String passUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.mailUsuario = mailUsuario;
        this.rolUsuario = "alumno"; // Define automáticamente el rol como "alumno"
        this.passUsuario = passUsuario; // Combina "alumno" con el ID del usuario
    }

    
////-----GETTER
    
    public Long getId() {
		return id;
	}
    public String getNombreUsuario() {
		return nombreUsuario;
	}
    public String getMailUsuario() {
		return mailUsuario;
    }
    public String getRolUsuario() {
		return rolUsuario;
	}
    
    public String getPassUsuario() {
		return passUsuario;
	}
	/*}public List<Reto> getRetos() {
		return retos;
	}*/
    public String getLetraClase() {
		return letraClase;
		
	}
	
	
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
	public void setMailUsuario(String mailUsuario) {
		this.mailUsuario = mailUsuario;
	}
	/*public void setRetos(List<Reto> retos) {
		this.retos = retos;
	}*/

	
	
	

}////---- FIN CLASE USUARIO

