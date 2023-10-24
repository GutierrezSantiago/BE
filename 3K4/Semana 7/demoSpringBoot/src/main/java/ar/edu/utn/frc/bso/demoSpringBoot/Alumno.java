package ar.edu.utn.frc.bso.demoSpringBoot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
public class Alumno {
    private int legajo;
    private String nombre;
    private int nota;

}
