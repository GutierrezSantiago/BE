package ar.edu.utn.frc.bso.servicioAlumnos.repositories;

import ar.edu.utn.frc.bso.servicioAlumnos.model.Alumno;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AlumnoRepository {

    private final List<Alumno> alumnos;

    public AlumnoRepository() {
        alumnos = new LinkedList<>();
    }

    public List<Alumno> getAll() {
        return alumnos;
    }

    public void add(Alumno a) {
        alumnos.add(a);
    }

}
