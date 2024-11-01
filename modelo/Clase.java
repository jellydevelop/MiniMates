package es.daw.proyectoDAW.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.daw.proyectoDAW.errores.UsuarioNoAlumnoException;
import es.daw.proyectoDAW.errores.UsuarioNoProfesorException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
//************************************************IMPORTS

@Entity(name = "CLASE")
public class Clase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Clase;
	
	
	@Column(name = "LETRA_CLASE", columnDefinition = "varchar(3)", nullable = false,unique = true)
	private String letraClase;

	/// -->> RELACIONES

    // Relación con Centro_Educativo
	@ManyToOne
	@JoinColumn(name = "id_centro",nullable=false)
	private Centro_Educativo centro;

    // Relación con Usuarios (alumnos y profesor)

	@OneToMany(mappedBy = "clase")//es la propiedad en Usuario
	private List<Usuario> alumnos = new ArrayList<>();

	@OneToOne(mappedBy = "claseComoProfesor") // atributo en Usuario 
    private Usuario profesor; 

	/// -->> CONSTRUCTOR VACÍO

	public Clase() {

	}

	/// -->> GETTERS



	public Long getIdClase() {
		return id_Clase;
	}

	
	public String getLetraClase() {
		return letraClase;
	}

	public Centro_Educativo getCentro() {
		return centro;
	}

	/// -->> SETTERS

	public void setIdClase(Long idClase) {
		this.id_Clase = idClase;
	}


	public void setCentro(Centro_Educativo centro) {
		this.centro = centro;
	}

	public void setLetraClase(String letraClase) {
		this.letraClase = letraClase;
	}
	

	/// -->> MÉTODOS PROPIOS
	
	//modificar profesor de una clase
	 public void setProfesor(Usuario profesor) throws UsuarioNoProfesorException {
	        if (profesor != null && profesor.esProfesor()) {
	            this.profesor = profesor;
	        } else {
	            throw new UsuarioNoProfesorException("El usuario asignado debe tener el rol de profesor.");
	        }
	    }
	  // obtener el usuario con rol 'profesor' de una clase
	    public Usuario getProfesor() {
	        return profesor != null && profesor.esProfesor() ? profesor : null;
	    }
	    
	    //lista alumnos  de una clase
		 public List<Usuario> getAlumnos() {
		        return alumnos.stream()
		                .filter(Usuario::esAlumno)
		                .collect(Collectors.toList());
		    }
		 
		 //este alumno petenece a esta clase
		 public boolean contieneAlumno(Usuario alumno) {
			    return alumnos.contains(alumno);
			}
		 
		 //este profesor imparte en esta clase
		 public String getNombreProfesorTitular() {
			    return profesor != null ? profesor.obtenerNombreCompleto() : "Sin profesor asignado";
			}
		 //obtener alumno de esta clase por nia
		 public Usuario obtenerAlumnoPorNIA(String nia) {
			    return alumnos.stream()
			            .filter(alumno -> alumno.esAlumno() && alumno.getNiaAlumno().equals(nia))
			            .findFirst()
			            .orElse(null);
			}
		 
		 //obtener alumnos de esta clase
		 public List<String> getNombresDeAlumnos() {
			    return alumnos.stream()
			            .filter(Usuario::esAlumno)
			            .map(Usuario::obtenerNombreCompleto)
			            .collect(Collectors.toList());
			}
		 
		 //elimina alumno de esta clase
		 public void removeAlumno(Usuario alumno) {
			    if (alumnos.contains(alumno)) {
			        alumnos.remove(alumno);
			        alumno.setClase(null); 
			    }
			}
		 
		 //añadir alumno a esta clase
		 public void addAlumno(Usuario alumno) throws UsuarioNoAlumnoException {
			    if (alumno != null && alumno.esAlumno()) {
			        alumnos.add(alumno);
			        alumno.setClase(this); 
			    } else {
			        throw new UsuarioNoAlumnoException("El usuario asignado debe tener el rol de alumno.");
			    }
			}
}
