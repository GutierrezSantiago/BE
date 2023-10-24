package ar.edu.utn.frc.bso.servicioAlumnos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    private int legajo;
    private String nombre;

}