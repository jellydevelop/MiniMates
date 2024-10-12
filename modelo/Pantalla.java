package es.daw.proyectoDAW.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Pantalla {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPantalla;
    
    @Column(name = "nombre_pantalla")
    private String nombrePantalla;
    
    // Otros atributos y métodos
    
    @ManyToOne
    private Mundo mundo;
}
