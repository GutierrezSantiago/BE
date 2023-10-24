package ar.edu.utn.frc.bso.servicioAlumnos.repositories;

import ar.edu.utn.frc.bso.servicioAlumnos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
