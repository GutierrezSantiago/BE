package utn.frc.backend.tutor.sac.web.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.tutor.sac.dal.AlumnoRepository;
import utn.frc.backend.tutor.sac.dal.PersonaRepository;
import utn.frc.backend.tutor.sac.domain.Alumno;
import utn.frc.backend.tutor.sac.domain.Persona;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnosAPI {

    // - Puede haber tantos repositorios (u objetos [beans] cuales quiera,
    //   a tal caso) inyectados como se desee/necesite (en este caso, inyección
    //   por constructor; ver abajo).
    // - Por otra parte, si este nro es considerable (convencional/subjetivo),
    //   queda más "prolijo" implementar capa de servicio.
    // - Más aún, incluso siendo reducido, es buena práctica implementarla.
    // - La cantidad de "servicios" que la conforman dependerá luego de la naturaleza
    //   de la aplicación, de su complejidad, del patrón de diseño implementado, de las
    //   preferencias, etc, etc...
    // - Siguiendo esa práctica, se implementará luego, a partir de DocentesAPI, capa
    //   de servicio.
    // - todo: Mencionar el tema en clase y describir anatomía/distribución
    //         "convencional" de servicios en un proyecto "típico".

    private AlumnoRepository rep;
    private PersonaRepository pRep;

    // --------------------------------------------------------
    // Constructor injection: better/flexible testability
    // (easy mockable).
    // --------------------------------------------------------
    @Autowired
    public AlumnosAPI(AlumnoRepository rep, PersonaRepository pRep) {
        this.rep = rep;
        this.pRep = pRep;
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> getAlumnos() {
        // todo: 204 en caso de vacío?
        return ResponseEntity.ok(rep.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumno(@PathVariable Integer id) {
        Alumno alumno = rep.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Alumno [%d] inexistente", id))
        );

        return ResponseEntity.ok(alumno);
    }

    @PostMapping
    // 200 / 400 / 404
    public ResponseEntity<Alumno> addAlumno(@RequestBody @Valid Alumno alumno) {
        if (!pRep.existsById(alumno.getPid())) {
            return new ResponseEntity<>(alumno, HttpStatus.NOT_FOUND);
            //throw new ResourceNotFoundException("pid inválido o persona inexistente");
        }

        rep.save(alumno);
        Persona persona = pRep.findById(alumno.getPid()).get();
        alumno.setPersona(persona);

        return ResponseEntity.ok(alumno);
    }

    @PutMapping("/{id}")
    // 200 / 404
    public ResponseEntity<Alumno> updateAlumno(
            @PathVariable Integer id,
            @Valid @RequestBody Alumno alumno
    ) {

        if (!rep.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Alumno [%d] inexistente", id));
        }

        return ResponseEntity.ok(rep.save(alumno));
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteAlumno(@PathVariable Integer id) {
        if (!rep.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404
        }

        rep.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }

}
