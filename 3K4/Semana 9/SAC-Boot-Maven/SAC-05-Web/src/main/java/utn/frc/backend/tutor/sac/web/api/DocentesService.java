package utn.frc.backend.tutor.sac.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.backend.tutor.sac.dal.DocenteRepository;
import utn.frc.backend.tutor.sac.dal.MateriaRepository;
import utn.frc.backend.tutor.sac.dal.PersonaRepository;
import utn.frc.backend.tutor.sac.domain.Docente;
import utn.frc.backend.tutor.sac.domain.Materia;

import java.util.List;
import java.util.Optional;

@Service
public class DocentesService {

    private PersonaRepository pRep;
    private DocenteRepository dRep;
    private MateriaRepository mRep;

    @Autowired
    public DocentesService(PersonaRepository pRep, DocenteRepository dRep, MateriaRepository mRep) {
        this.pRep = pRep;
        this.dRep = dRep;
        this.mRep = mRep;
    }

    public boolean existsById(Integer pid) {
        return dRep.existsById(pid);
    }

    public boolean existsPersonaById(Integer pid) {
        return pRep.existsById(pid);
    }

    public List<Docente> findAll() {
        return dRep.findAll();
    }

    public Optional<Docente> findById(Integer pid) {
        return dRep.findById(pid);
    }

    public List<Materia> findMaterias(Integer pid) {
        // Ver declaración en MateriaRepository
        return mRep.findMateriasByDocentesPid(pid);
    }

    public Docente save(Docente docente) {
        return dRep.save(docente);
    }

    public boolean deleteById(Integer pid) {
        if (!dRep.existsById(pid)) {
            return false;
        }

        dRep.deleteById(pid);
        return true;
    }
}
