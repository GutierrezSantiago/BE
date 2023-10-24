package ar.edu.utn.frc.bso.servicioAlumnos.services;

import ar.edu.utn.frc.bso.servicioAlumnos.exceptions.InvalidDataException;
import ar.edu.utn.frc.bso.servicioAlumnos.model.Alumno;
import ar.edu.utn.frc.bso.servicioAlumnos.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    private AlumnoRepository repo;

    @Autowired
    public AlumnoService(AlumnoRepository repo) {
        this.repo = repo;
    }

    public Alumno getMejorAlumno() {
        return new Alumno(1234, "Einstein");
    }

    public List<Alumno> getAllAlumnos() {
        return repo.getAll();
    }

    public void addAlumno(Alumno a) {
        if (a.getNombre().isBlank()) {
            throw new InvalidDataException("Nombre Inválido");
        }
        repo.add(a);
    }

    public Optional<Alumno> getAlumno(int legajo) {
        List<Alumno> alumnos = repo.getAll();
        return alumnos.stream()
                .filter(x -> x.getLegajo() == legajo)
                .findFirst();
    }
}
