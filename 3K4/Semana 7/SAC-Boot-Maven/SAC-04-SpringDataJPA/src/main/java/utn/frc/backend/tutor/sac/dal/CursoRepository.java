package utn.frc.backend.tutor.sac.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import utn.frc.backend.tutor.sac.domain.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

    // Como ejemplo, para mostrar funcionalidad
    @Transactional
    @Modifying
    @Query(
            value = "insert into inscripcion(cid, pid) values (:cid, :pid)",
            nativeQuery = true
    )
    public void saveInscripcion(@Param("cid") Integer cid, @Param("pid") Integer pid);

}
