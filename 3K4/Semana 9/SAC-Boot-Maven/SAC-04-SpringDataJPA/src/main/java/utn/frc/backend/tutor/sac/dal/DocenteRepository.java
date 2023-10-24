package utn.frc.backend.tutor.sac.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frc.backend.tutor.sac.domain.Docente;

import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {

    List<Docente> findDocentesByMateriasMid(Integer mid);

}
