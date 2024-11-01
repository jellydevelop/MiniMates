package es.daw.proyectoDAW.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private List<Clase> clases;

	/*@OneToMany(mappedBy = "centro")
	private List<Usuario> usuarios;*/

/// -->> CONSTRUCTOR VACÍO
	public Centro_Educativo() {
	    //this.usuarios = new ArrayList<>();
	    this.clases = new ArrayList<>();
	}
	/// -->> CONSTRUCTOR con ID

	public Centro_Educativo(Long id) {
	    this.idCentro = id;
	   // this.usuarios = new ArrayList<>();
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

	  // lista de profesores filtrando usuarios con rol "profesor".
  /*  public List<Usuario> getProfesores() {
        return usuarios.stream()
                .filter(Usuario::esProfesor)
                .collect(Collectors.toList());
    }
    
    // Obtiene la lista de alumnos filtrando usuarios con rol "alumno".
    public List<Usuario> getAlumnos() {
        return usuarios.stream()
                .filter(Usuario::esAlumno)
                .collect(Collectors.toList());
    }
*/
	public List<Clase> getClases() {
		return clases;
	}
	
/*	public List<Usuario> getUsuarios() {
        return usuarios;
    }*/

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

	/* public void setUsuarios(List<Usuario> usuarios) {
	        this.usuarios = usuarios;
	    }*/


	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}
	
	
	///-->> MÉTODOS PROPIOS
	
	// añadir un usuario al centro./*
/*	public void aniadeUsuario(Usuario usuario) {
	    usuarios.add(usuario);
	    usuario.setCentro(this);
	}

    // elimina un usuario del centro.
    public void eliminaUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        usuario.setCodigoCentro(null);
    }*/

}
