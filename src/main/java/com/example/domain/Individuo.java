package com.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import lombok.Data;

@Data // La anotación lombok @Data genera automáticamente los métodos equals, hashCode, toString, getters y setters para los campos de la clase.
@Entity // Indica que esta clase es una entidad que se mapeará a una tabla en la base de datos.
@Table(name="individuo") // Especifica el nombre de la tabla en la base de datos a la que se va a mapear la entidad.
public class Individuo implements Serializable {
   
    private static final long serialVersionUID = 1L;
    
    @Id // Indica que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define la estrategia de generación de valores para la clave primaria. En este caso, se utilizará una columna de identidad de la base de datos.
    private Long id; // Campo que representa la clave primaria de la entidad.
    
    //anotación @NotEmpty que requiere dependencia spring-boot-starter-validation
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    @NotEmpty
    @Email
    private String correo;
    @NotEmpty
    private String edad;
    @NotEmpty
    private String telefono;
    
}
