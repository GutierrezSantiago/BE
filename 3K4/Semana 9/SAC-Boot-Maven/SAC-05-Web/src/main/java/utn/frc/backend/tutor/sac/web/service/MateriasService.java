package utn.frc.backend.tutor.sac.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.backend.tutor.sac.dal.DocenteRepository;
import utn.frc.backend.tutor.sac.dal.MateriaRepository;
import utn.frc.backend.tutor.sac.domain.Docente;
import utn.frc.backend.tutor.sac.domain.Materia;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class MateriasService {

    private DocenteRepository dRep;
    private MateriaRepository mRep;

    @Autowired
    public MateriasService(DocenteRepository dRep, MateriaRepository mRep) {
        this.dRep = dRep;
        this.mRep = mRep;
    }

    public boolean existsById(Integer mid) {
        return mRep.existsById(mid);
    }

    public List<Materia> findAll() {
        return mRep.findAll();
    }

    public Optional<Materia> findById(Integer mid) {
        return mRep.findById(mid);
    }

    public List<Docente> findDocentes(Integer mid) {
        // Ver declaración en DocenteRepository
        return dRep.findDocentesByMateriasMid(mid);
    }

    public Materia save(Materia materia) {
        return mRep.save(materia);
    }

    public Docente addDocente(Integer mid, Integer pid) {
        Materia materia = mRep.findById(mid).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Materia %d inexistente", mid))
        );

        Docente docente = dRep.findById(pid).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Docente %d inexistente", pid))
        );

        materia.addDocente(docente);

        mRep.save(materia);

        return docente;
    }

    public boolean deleteById(Integer mid) {
        if (!mRep.existsById(mid)) {
            return false;
        }

        mRep.deleteById(mid);
        return true;
    }

    public boolean deleteDocente(Integer mid, Integer pid) {
        Materia materia = mRep.findById(mid).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Materia %d inexistente", mid))
        );

        boolean r = materia.removeDocente(pid);

        if (r) {
            mRep.save(materia);
        }

        return r;
    }





}
