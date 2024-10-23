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
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    	private Long id;
    
    @Column(name="NIA_USUARIO",columnDefinition = "char(8)", nullable = true)
    private Character niaUsuario;
    
    @Column(name="CODIGO_CENTRO", columnDefinition = "varchar(10)", nullable = true)
    private String codigoCentro; 
    
    @Column(name="PRIMER_APELLIDO_USUARIO",columnDefinition="varchar(25)")
    private String primApellidoUsuario;
	
	@Column(name="SEGUNDO_APELLIDO_USUARIO",columnDefinition="varchar(25)")
    private String secApellidoUsuario;
    
	@Column(name="NOMBRE_USUARIO",columnDefinition="varchar(20)")
    private String nombreUsuario;
	
	
	@Column(name="MAIL_USUARIO",columnDefinition="varchar(25)")
    private String mailUsuario;
	
	
	@Column(name="PASS_USUARIO",columnDefinition="varchar(10)")
	private String passUsuario;
	
	@Column(name="ROL_USUARIO",columnDefinition="varchar(10)")
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
////----CONSTR PARA DAR DE ALTA A ALUMNOS

    public Usuario(String nombreUsuario, String mailUsuario, String passUsuario, String primApellidoUsuario, String secApellidoUsuario, String letraClase) {
        this.nombreUsuario = nombreUsuario;
        this.mailUsuario = mailUsuario;
        this.passUsuario = passUsuario;
        this.rolUsuario = "alumno"; 
        this.primApellidoUsuario = primApellidoUsuario;
        this.secApellidoUsuario = secApellidoUsuario;
        this.letraClase=letraClase;
    }    
    
////----CONSTR PARA DAR DE ALTA A PROFESOR

        public Usuario(String nombreUsuario, String mailUsuario, String passUsuario, String letraClase) {
            this.nombreUsuario = nombreUsuario;
            this.mailUsuario = mailUsuario;
            this.passUsuario = passUsuario;
            this.rolUsuario = "profesor"; 
            this.letraClase=letraClase;

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
    public Character getNiaUsuario() {
		return niaUsuario;
	}
    
    public String getPrimApellidoUsuario() {
		return primApellidoUsuario;
	}
    
    public String getSecApellidoUsuario() {
		return secApellidoUsuario;
	}
    
	
	
////-----SETTER
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setPassUsuario(String passUsuario) {
		this.passUsuario = passUsuario;
	}
	
	public void setPrimApellidoUsuario(String primApellidoUsuario) {
		this.primApellidoUsuario = primApellidoUsuario;
	}
	public void setSecApellidoUsuario(String secApellidoUsuario) {
		this.secApellidoUsuario = secApellidoUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public void setRolUsuario(String rolUsuario) {
		this.rolUsuario = rolUsuario;
	}
	
	public void setMailUsuario(String mailUsuario) {
		this.mailUsuario = mailUsuario;
	} 
	
	public void setNiaUsuario(Character niaUsuario) {
		this.niaUsuario = niaUsuario;
	}
	
	
	/*public void setRetos(List<Reto> retos) {
		this.retos = retos;
	}*/

	
	
	

}////---- FIN CLASE USUARIO

