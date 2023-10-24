package utn.frc.backend.tutor.sac.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frc.backend.tutor.sac.domain.Materia;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {

    List<Materia> findMateriaByDocentesPid(Integer pid);

}
