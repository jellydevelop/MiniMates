package es.daw.proyectoDAW.modelo;

import java.util.List;

import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import es.daw.proyectoDAW.errores.AtributoNuloException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.Inheritance;
//import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//------------------------------------
//------------------------------------
@Entity
@Table(name = "Usuario")
//@Inheritance(strategy = InheritanceType.JOINED) // HERENCIA --> ESTA CLASE ES PADRE
@NamedQuery(name = "USUARIO.findByEmailAddress", query = "select u from Usuario u where u.mailUsuario = ?1")
public  class Usuario {
	

	 public static final String ROL_PROFESOR = "PROFESOR";
	 public static final String ROL_ALUMNO = "ALUMNO";
	    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(name = "CODIGO_CENTRO", columnDefinition = "varchar(8)", nullable = false)
	private String codigoCentro;

	@Column(name = "PRIMER_APELLIDO_USUARIO", columnDefinition = "varchar(25)", nullable = false)
	private String primApellidoUsuario;

	@Column(name = "SEGUNDO_APELLIDO_USUARIO", columnDefinition = "varchar(25)", nullable = false)
	private String secApellidoUsuario;

	@Column(name = "NOMBRE_USUARIO", columnDefinition = "varchar(20)", nullable = false)
	private String nombreUsuario;

	@Column(name = "MAIL_USUARIO", columnDefinition = "varchar(35)", nullable = false)
	private String mailUsuario;

	@Column(name = "PASS_USUARIO", columnDefinition = "varchar(10)", nullable = false)
	private String passUsuario;

	@Column(name = "ROL_USUARIO", columnDefinition = "varchar(10)", nullable = false)
	private String rolUsuario;
	
	//////////////alumno
	@Column(name = "NIA_ALUMNO", nullable = true, columnDefinition = "varchar(8)")
	private String niaAlumno;
	
	

	// ------ RELACIONES
	
	   // Relación con Clase-alumnos
 @ManyToOne
   @JoinColumn(name = "LETRA_CLASE", nullable = true) //FK a clase
 // Evita la serialización de la clase cuando se consulte el usuario
 @JsonBackReference
   private Clase clase;
	
		  //relación con pa+ida
 @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
 @JsonIgnore // Evita la serialización de las partidas cuando se consulte el usuario
 private List<Partida> partidas;
	
	
	// ---- CONSTRUCTOR VACÍO
	public Usuario() {
	}

	// ---- CONSTRUCTOR PARA DAR DE ALTA A ALUMNOS
	public Usuario(String nombreUsuario, String mailUsuario, String passUsuario, String primApellidoUsuario,
            String secApellidoUsuario, String nia, Clase clase) {
this.nombreUsuario = nombreUsuario;
this.mailUsuario = mailUsuario;
this.passUsuario = passUsuario;
this.rolUsuario = ROL_ALUMNO;
this.primApellidoUsuario = primApellidoUsuario;
this.secApellidoUsuario = secApellidoUsuario;
this.niaAlumno = nia;
this.clase = clase;
}
	// ---- CONSTRUCTOR PARA DAR DE ALTA A ALUMNOS

public Usuario(String nombreUsuario, String mailUsuario, String passUsuario, Clase clase) {
this.nombreUsuario = nombreUsuario;
this.mailUsuario = mailUsuario;
this.passUsuario = passUsuario;
this.rolUsuario = ROL_PROFESOR;
this.clase = clase;

}

    // ---- CONSTRUCTOR PARA VALIDACIÓN DE LOGIN
    public Usuario(String mailUsuario, String passUsuario) throws AtributoNuloException {
    	
        if (mailUsuario == null || mailUsuario.trim().isEmpty()) {
            throw new AtributoNuloException("El correo electrónico no puede ser nulo o vacío");
        }
        if (passUsuario == null || passUsuario.trim().isEmpty()) {
            throw new AtributoNuloException("La contraseña no puede ser nula o vacía");
        }

        this.mailUsuario = mailUsuario;
        this.passUsuario = passUsuario;
    }

	// ----- GETTERS
	public Long getIdUsuario() {
		return idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getCodigoCentro() {
		return codigoCentro;
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

	public String getPrimApellidoUsuario() {
		return primApellidoUsuario;
	}

	public String getSecApellidoUsuario() {
		return secApellidoUsuario;
	}
	public String getNiaAlumno() {
		return niaAlumno;
	}
	
	 public void setClaseUsuario(Clase clase) {
	        this.clase = clase;
	    }

	
	public Clase getClase() {
		return clase;
	}
	
	public List<Partida> getPartidas() {
		return partidas;
	}
	
	public static String getRolAlumno() {
		return ROL_ALUMNO;
	}
	
	public static String getRolProfesor() {
		return ROL_PROFESOR;
	}
	

	// ----- SETTERS
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
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
	
	public void setNiaAlumno(String niaAlumno) {
		this.niaAlumno = niaAlumno;
	}
	
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	
	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	// ------ MÉTODOS PROPIOS
	
	//equals
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	//toString
	@Override
	public String toString() {
		return super.toString();
	}
	

	// obtiene nombre completo
	public String obtenerNombreCompleto() {
		return String.format("%s %s %s", nombreUsuario, primApellidoUsuario, secApellidoUsuario);
	}

	// verifica rol alumno
	public boolean esAlumno() {
		return "alumno".equals(rolUsuario);
	}

	// verifica rol profesor
	public boolean esProfesor() {
		return "profesor".equals(rolUsuario);
	}
 

	
}
