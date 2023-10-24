package utn.frc.backend.tutor.sac.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frc.backend.tutor.sac.domain.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
}
