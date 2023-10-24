package ar.edu.utn.frc.bso.demoSpringBoot;

import java.util.List;

public interface RepositorioAlumnos {

    List<Alumno> listar();

    void agregar(Alumno a);


}
