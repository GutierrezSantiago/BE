package utn.frc.backend.tutor.sac.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frc.backend.tutor.sac.domain.Inscripcion;
import utn.frc.backend.tutor.sac.domain.InscripcionPK;

public interface InscripcionRepository extends JpaRepository<Inscripcion, InscripcionPK> {
}
