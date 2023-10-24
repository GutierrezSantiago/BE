package utn.frc.backend.tutor.sac.web.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.tutor.sac.dal.DocenteRepository;
import utn.frc.backend.tutor.sac.dal.MateriaRepository;
import utn.frc.backend.tutor.sac.domain.Docente;
import utn.frc.backend.tutor.sac.domain.Materia;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;
import utn.frc.backend.tutor.sac.web.service.MateriasService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/materias")
public class MateriasAPI {

    private MateriasService service;

    @Autowired
    public MateriasAPI(MateriasService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Materia>> getMaterias() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> getMateria(@PathVariable Integer id) {
        Materia materia = service.findById(id).orElseThrow(
                () -> new ResourceNotFoundException()
        );

        return ResponseEntity.ok(materia);
    }

    @GetMapping("/{id}/docentes")
    public ResponseEntity<Set<Docente>> getDocentes(@PathVariable Integer id) {
        Materia materia = service.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Materia %d inexistente", id))
        );

        return materia.getDocentes().isEmpty()
                ? new ResponseEntity(HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(materia.getDocentes());
    }

    // Alternativa
    @GetMapping("/{id}/docentes/alt")
    public ResponseEntity<List<Docente>> getDocentesAlt(@PathVariable Integer id) {
        if (!service.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Materia %d inexistente", id));
        }

        List<Docente> docentes = service.findDocentes(id);

        return docentes.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(docentes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Materia> addMateria(@RequestBody @Valid Materia materia) {
        return ResponseEntity.ok(service.save(materia));
    }

    @PostMapping("/{mid}/docentes/{pid}") // Simple vinculación ==> NO RequestBody
    public ResponseEntity<Docente> addDocente(@PathVariable Integer mid, @PathVariable Integer pid) {
        return ResponseEntity.ok(service.addDocente(mid, pid));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> updateMateria(
            @PathVariable Integer id,
            @Valid @RequestBody Materia materia
    ) {

        if (!service.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Materia [%d] inexistente", id));
        }

        return ResponseEntity.ok(service.save(materia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMateria(@PathVariable Integer id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{mid}/docentes/{pid}")
    public ResponseEntity deleteMateria(@PathVariable Integer mid, @PathVariable Integer pid) {
        return service.deleteDocente(mid, pid)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
