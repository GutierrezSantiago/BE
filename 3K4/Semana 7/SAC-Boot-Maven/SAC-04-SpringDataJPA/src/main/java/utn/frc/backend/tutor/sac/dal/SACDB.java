package utn.frc.backend.tutor.sac.dal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.backend.tutor.sac.domain.*;

@Service
public class SACDB {
    @PersistenceContext
    private EntityManager em; //todo: implementar algún ejemplo.

    private PersonaRepository pRep;
    private AlumnoRepository aRep;
    private DocenteRepository dRep;
    private MateriaRepository mRep;
    private CursoRepository cRep;
    private InscripcionRepository iRep;

    @Autowired
    public SACDB(
            PersonaRepository pRep,
            AlumnoRepository aRep,
            DocenteRepository dRep,
            MateriaRepository mRep,
            CursoRepository cRep,
            InscripcionRepository iRep
    ) {
        this.pRep = pRep;
        this.aRep = aRep;
        this.dRep = dRep;
        this.mRep = mRep;
        this.cRep = cRep;
        this.iRep = iRep;
    }

    public Curso saveCurso(Curso curso) {
        if (curso.getMateria() == null) {
            Materia m = mRep.findById(curso.getMid()).orElseThrow(
                    () -> new RuntimeException("Materia no encontrada")
            );
            curso.setMateria(m);
        }
        if (curso.getDocente() == null) {
            Docente d = dRep.findById(curso.getPid()).orElseThrow(
                    () -> new RuntimeException("Docente no encontrado")
            );
            curso.setDocente(d);
        }

        return cRep.save(curso);
    }

    public Alumno saveInscripcion(Integer cid, Integer pid) {
        Curso curso = cRep.findById(cid).orElseThrow(
                () -> new RuntimeException(String.format("Curso %d inexistente", cid))
        );

        Alumno alumno = aRep.findById(pid).orElseThrow(
                () -> new RuntimeException(String.format("Alumno %d inexistente", pid))
        );

        Inscripcion inscripcion = new Inscripcion(cid, pid);
        iRep.save(inscripcion);

        return alumno;
    }

}
