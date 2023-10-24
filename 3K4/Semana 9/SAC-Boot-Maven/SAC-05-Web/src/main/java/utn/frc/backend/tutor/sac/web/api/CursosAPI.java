package utn.frc.backend.tutor.sac.web.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.tutor.sac.dal.*;
import utn.frc.backend.tutor.sac.domain.*;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/cursos")
public class CursosAPI {

    private CursoRepository rep;
    private SACDB db;

    @Autowired
    public CursosAPI(CursoRepository rep, SACDB db) {
        this.rep = rep;
        this.db = db;
    }

    @GetMapping
    public List<Curso> getCursos() {
        return rep.findAll();
    }

    @GetMapping("/{id}")
    public Curso getCurso(@PathVariable Integer id) {
        Curso curso = rep.findById(id).orElseThrow(
                // lambda
                () -> new ResourceNotFoundException()
        );

        return curso;
    }

    @DeleteMapping("/{id}")
    void deleteCurso(@PathVariable Integer id) {
        rep.deleteById(id);
    }

    @PostMapping
    public Curso addCurso(@Valid @RequestBody Curso curso) {
        return db.saveCurso(curso);
    }

    @PutMapping("/{id}")
    public Curso updateCurso(
            @PathVariable Integer id,
            @Valid @RequestBody Curso curso
    ) {
        return db.saveCurso(curso);
    }

    @GetMapping("/{id}/alumnos")
    public Set<Alumno> getCursoAlumnos(@PathVariable Integer id) {
        Curso curso = rep.findById(id).orElseThrow(
                // lambda
                () -> new ResourceNotFoundException()
        );

        return curso.getAlumnos();
    }

    @PostMapping("/{cid}/alumnos/{pid}") // Simple vinculación ==> NO RequestBody
    public Alumno addInscripcion(@PathVariable Integer cid, @PathVariable Integer pid) {
        return db.saveInscripcion(cid, pid);
    }

}
