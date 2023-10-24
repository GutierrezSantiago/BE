package ar.edu.utn.frc.bso.servicioAlumnos.services;

import ar.edu.utn.frc.bso.servicioAlumnos.entities.Alumno;
import ar.edu.utn.frc.bso.servicioAlumnos.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnosService {

    private AlumnoRepository repository;

    @Autowired
    public AlumnosService(AlumnoRepository repository) {
        this.repository = repository;
    }

    public List<Alumno> getAlumnosRegulares() {
        List<Alumno> all = repository.findAll();
        return all.stream()
                .filter(x -> promedio(x) >= 4)
                .toList();
    }


    public List<Alumno> getAllAlumnos() {
        return repository.findAll();
    }

    public double promedio(Alumno a) {
        if (a == null) {
            throw new IllegalArgumentException();
        }
        int suma = a.getNota1() + a.getNota2() + a.getNota3();
        return suma / 3.0;
    }
}
