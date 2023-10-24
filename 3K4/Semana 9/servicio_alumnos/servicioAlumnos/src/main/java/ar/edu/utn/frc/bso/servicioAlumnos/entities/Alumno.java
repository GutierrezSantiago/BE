package ar.edu.utn.frc.bso.servicioAlumnos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Alumnos")
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    @Id
    private int legajo;
    private String nombre;
    private int nota1;
    private int nota2;
    private int nota3;
}
