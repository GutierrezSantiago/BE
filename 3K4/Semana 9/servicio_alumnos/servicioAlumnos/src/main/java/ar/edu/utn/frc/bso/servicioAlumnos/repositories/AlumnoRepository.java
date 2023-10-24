package ar.edu.utn.frc.bso.servicioAlumnos.repositories;

import ar.edu.utn.frc.bso.servicioAlumnos.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

    Optional<Alumno> getAlumnoByNombre(String nombre);
}
