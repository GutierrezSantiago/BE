package utn.frc.backend.tutor.sac.web.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.tutor.sac.domain.Docente;
import utn.frc.backend.tutor.sac.domain.Materia;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/docentes")
public class DocentesAPI {

    private DocentesService service;

    @Autowired
    public DocentesAPI(DocentesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Docente>> getDocentes() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocente(@PathVariable Integer id) {
        Docente docente = service.findById(id).orElseThrow(
                () -> new ResourceNotFoundException()
        );

        return ResponseEntity.ok(docente);
    }

    @GetMapping("/{id}/materias")
    public ResponseEntity<Set<Materia>> getMaterias(@PathVariable Integer id) {
        Docente docente = service.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Docente %d inexistente", id))
        );

        return docente.getMaterias().isEmpty()
                ? new ResponseEntity(HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(docente.getMaterias());
    }

    // Alternativa
    @GetMapping("/{id}/materias/alt")
    public ResponseEntity<List<Materia>> getDocenteMateriasVariante(@PathVariable Integer id) {
        if (!service.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Docente %d inexistente", id));
        }

        List<Materia> materias = service.findMaterias(id);

        return materias.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(materias, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Docente> addDocente(@RequestBody @Valid Docente docente) {
        if (!service.existsPersonaById(docente.getPid())) {
            return new ResponseEntity<>(docente, HttpStatus.NOT_FOUND);
            //throw new ResourceNotFoundException(String.format("Persona %d inexistente", id));
        }

        return ResponseEntity.ok(service.save(docente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> updateDocente(
            @PathVariable Integer id,
            @Valid @RequestBody Docente docente
    ) {

        if (!service.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Docente [%d] inexistente", id));
        }

        return ResponseEntity.ok(service.save(docente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDocente(@PathVariable Integer id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
