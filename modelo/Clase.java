package es.daw.proyectoDAW.modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.daw.proyectoDAW.errores.UsuarioNoAlumnoException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
//************************************************IMPORTS
import jakarta.persistence.Table;

@Entity
@Table(name = "CLASE")
public class Clase {
	
	@Id
    @Column(name = "LETRA_CLASE", columnDefinition = "varchar(3)", nullable = false, unique = true)
    private String letraClase;
	
	

	/// -->> RELACIONES

    // -- Relación con Centro_Educativo
	@ManyToOne
	@JoinColumn(name = "id_centro",nullable=false)
	 // Este lado NO se serializa
 //evita  recursion infinita entre clase y centro
	 //objeto de vuelta intifinito al devolver delete usuario
	private Centro_Educativo centro;

    // - Relación con Usuarios (alumnos y profesor)

	@OneToMany(mappedBy = "clase",cascade = CascadeType.ALL)//es la propiedad en Usuario
	@JsonManagedReference // Este lado se serializa
	private List<Usuario> usuarios = new ArrayList<>();


	/// -->> CONSTRUCTOR VACÍO

	public Clase() {

	}
	/// -->> CONSTRUCTOR PARA DESERISLIZAR LETRA

	public Clase(String letraClase) {
	    this.letraClase = letraClase;
	}
	
	/// -->> CONSTRUCTOR COMPLETO

	 public Clase(String letraClase, Centro_Educativo centro) {
	        this.letraClase = letraClase;
	        this.centro = centro;
	    }

	/// -->> GETTERS

	
	public String getLetraClase() {
		return letraClase;
	}

	public Centro_Educativo getCentro() {
		return centro;
	}
	/// -->> SETTERS


	public void setCentro(Centro_Educativo centro) {
		this.centro = centro;
	}

	public void setLetraClase(String letraClase) {
		this.letraClase = letraClase;
	}

	/// -->> MÉTODOS PROPIOS

	   public void addUsuario(Usuario usuario) {
		   usuarios.add(usuario);
	        usuario.setClase(this);
	    }    
	
	    //lista alumnos  de una clase
		 public List<Usuario> getAlumnos() {
		        return usuarios.stream()
		                .filter(Usuario::esAlumno)
		                .collect(Collectors.toList());
		    }
		 
		 //este alumno petenece a esta clase
		 public boolean contieneAlumno(Usuario alumno) {
			    return usuarios.contains(alumno);
			}
		 
		 //obtener lista de profesores
		  public List<Usuario> obtenerProfesores() {
		        return usuarios.stream().filter(Usuario::esProfesor).collect(Collectors.toList());
		    }
		 //obtener alumno de esta clase por nia
		 public Usuario obtenerAlumnoPorNIA(String nia) {
			    return usuarios.stream()
			            .filter(alumno -> alumno.esAlumno() && alumno.getNiaAlumno().equals(nia))
			            .findFirst()
			            .orElse(null);
			}
		 
		 //obtener alumnos de esta clase
		 public List<String> getNombresDeAlumnos() {
			    return usuarios.stream()
			            .filter(Usuario::esAlumno)
			            .map(Usuario::obtenerNombreCompleto)
			            .collect(Collectors.toList());
			}
		 
		 //elimina alumno de esta clase
		 public void removeAlumno(Usuario alumno) {
			    if (usuarios.contains(alumno)) {
			    	usuarios.remove(alumno);
			        alumno.setClase(null); 
			    }
			}
		 
		 //añadir alumno a esta clase
		 public void addAlumno(Usuario alumno) throws UsuarioNoAlumnoException {
			    if (alumno != null && alumno.esAlumno()) {
			    	usuarios.add(alumno);
			        alumno.setClase(this); 
			    } else {
			        throw new UsuarioNoAlumnoException("El usuario asignado debe tener el rol de alumno.");
			    }
			}

		public Object getLetraAula() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public void addAlumnoAClase(Usuario alumno) throws UsuarioNoAlumnoException {
			
	        if (alumno == null || !alumno.esAlumno()) {
	            throw new UsuarioNoAlumnoException("El usuario asignado debe tener el rol de alumno.");
	        }
	        usuarios.add(alumno);
	        alumno.setClase(this); 
	    }

		public Clase get() {
			return this;
		}
		
	    public List<Usuario> obtenerAlumnos() {
	        return usuarios.stream().filter(Usuario::esAlumno).collect(Collectors.toList());
	    }

	    


		
}
