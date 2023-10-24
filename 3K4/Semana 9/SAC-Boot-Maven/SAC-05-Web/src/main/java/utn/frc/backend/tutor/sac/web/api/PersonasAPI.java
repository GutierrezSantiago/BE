package utn.frc.backend.tutor.sac.web.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.tutor.sac.dal.PersonaRepository;
import utn.frc.backend.tutor.sac.domain.Persona;
import utn.frc.backend.tutor.sac.web.api.exception.APIErrorEntity;
import utn.frc.backend.tutor.sac.web.api.exception.BadRequestException;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

@Validated // ver post all
@RestController
@RequestMapping("/personas")
public class PersonasAPI {

    private static final String EXCEPTION_HANDLED_BY = "PersonasAPI";

    // ------------------------------------------------------------------------
    // Field injection: poor/complicated testability.
    // ------------------------------------------------------------------------
    @Autowired
    private PersonaRepository rep;


    // ========================================================================
    // Alternativa Objetos, retornando:
    //     - en caso de éxito:
    //          - objetos del dominio (o colecciones de objetos), los cuales
    //            son finalmente serializados al formato destino (gralmente
    //            json).
    //     - en casos de error/excepción o especiales:
    //          - excepciones (gralmente custom) que, a su vez, gestionadas
    //            o no, terminan por retornaren objetos (wrappers) de tipo
    //            ResponseEntity<> (también serializados).
    // ========================================================================
    @GetMapping
    public List<Persona> getPersonas() {
        return rep.findAll();
    }

    // Conceptos:
    //   - Null safety
    //   - Optional (es lo que retorna findById())
    //       - Alternativa tratamiento (ver abajo):
    //           - Opción 1: Chequeo previo y consumo mediante .get()
    //           - Opción 2: Consumo mediante interfaz funcional
    //               - mediante lambda
    //               - mediante implementación (clase) anónima

    // Opción 1: chequeo previo y consumo mediante .get().
    @GetMapping("/{id}/optional")
    public Persona getPersonaOptional(@PathVariable Integer id) {
        Optional<Persona> p = rep.findById(id);

        if (p.isEmpty()) {
            throw new ResourceNotFoundException("Persona [" + id + "] inexistente");
        }

        return p.get();
    }

    // Opción 2: consumo mediante interfaz funcional.
    @GetMapping("/{id}")
    public Persona getPersonaIF(@PathVariable Integer id) throws Throwable {
        Persona p = rep.findById(id).orElseThrow(
                // 2.1: mediante lambda
                //        - comentado en favor de implementación (clase)
                //          anónima (fines demostrativos simplemente)
                //        - Lo que usaremos en adelante
                //() -> new ResourceNotFoundException(String.format("Persona [%d] inexistente", id))

                // 2.2: mediante implementación (clase) anónima
                //        - fines demostrativos simplemente
                //        - prestar atención a "throws Throwable" en la
                //          rúbrica
                new Supplier<Throwable>() {
                   @Override
                   public Throwable get() {
                       return new ResourceNotFoundException(String.format("Persona [%d] inexistente", id));
                   }
                }
        );

        return p;
    }

    // Conceptos:
    //   - Validación:
    //       - Validación manual (con Optional, por ejemplo)
    //       - Validación gestionada (ver pom.xml y anotaciones de clase)

    // Opción 1: Validación manual (usando Optional)
    //   - empty    --> 400 (Bad Request)
    //   - invalid  --> potencial 500
    @PostMapping("/validacion-manual")
    public Persona addPersonaValManual(@RequestBody Optional<Persona> persona) {
        // validación 1
        if (persona.isEmpty()) {
            throw new BadRequestException("Datos de persona faltantes");
        }

        // validación 2
        // validación ...
        // validación n

        return rep.save(persona.get());
    }

    // Opción 2: Validación gestionada
    //   - empty    --> 400 (Bad Request)
    //   - invalid  --> 400 (Bad Request)
    @PostMapping
    public Persona addPersonaValGestionada(@RequestBody @Valid Persona persona) {
        return rep.save(persona);
    }

    // todo: bulk insert?
    @PostMapping("/all")
    public List<Persona> addPersonas(
            // ver @Validated
            @NotNull(message = "Lista NO deber ser nula")
            @NotEmpty(message = "Lista NO deber ser vacía")
            @RequestBody
            List<@Valid Persona> personas
    ) {
        return rep.saveAll(personas);
    }

    // ========================================================================
    // Alternativa ResponseEntity<> (wrapper), siempre (éxito y/o error)
    // ========================================================================
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(
            @PathVariable Integer id,
            @RequestBody @Valid Persona persona
    ) {

        Persona p = rep.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Persona [%d] inexistente", id))
        );

        return ResponseEntity.ok(rep.save(p.update(persona)));
    }

    // todo: implementamos patch (actualización parcial)?
    //@PatchMapping
    //...

    @DeleteMapping("/{id}")
    public ResponseEntity deletePersona(@PathVariable Integer id) {
        if (!rep.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404
        }

        rep.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }

    //@ExceptionHandler(ResourceNotFoundException.class)
    //public ResponseEntity handleResourceNotFound(
    //        ResourceNotFoundException e,
    //        HttpServletRequest request
    //) {
    //
    //    String error = e.getMessage();
    //
    //    APIErrorEntity apiError = new APIErrorEntity(
    //            EXCEPTION_HANDLED_BY,
    //            "(Custom)ResourceNotFoundException (@ExceptionHandler)",
    //            null,
    //            HttpStatus.NOT_FOUND,
    //            request.getRequestURI(),
    //            e.getLocalizedMessage(),
    //            error
    //    );
    //
    //    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    //}
    //
    //@ExceptionHandler(BadRequestException.class)
    //public ResponseEntity handleBadRequestException(
    //        BadRequestException e,
    //        HttpServletRequest request
    //) {
    //
    //    String error = e.getMessage();
    //
    //    APIErrorEntity apiError = new APIErrorEntity(
    //            EXCEPTION_HANDLED_BY,
    //            "(Custom)BadRequestException (@ExceptionHandler)",
    //            null,
    //            HttpStatus.BAD_REQUEST,
    //            request.getRequestURI(),
    //            e.getLocalizedMessage(),
    //            error
    //    );
    //
    //    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    //}
    //
    //@ExceptionHandler(ConstraintViolationException.class)
    //public ResponseEntity<Object> handleConstraintViolation(
    //        ConstraintViolationException ex,
    //        HttpServletRequest request
    //) {
    //
    //    Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
    //    List<String> errors = new ArrayList<String>();
    //
    //    // for simple
    //    for (ConstraintViolation<?> violation : violations) {
    //        errors.add(
    //                violation.getRootBeanClass().getName() + " " +
    //                        violation.getPropertyPath() + ": " + violation.getMessage()
    //        );
    //    }
    //
    //    APIErrorEntity apiError = new APIErrorEntity(
    //            EXCEPTION_HANDLED_BY,
    //            "ConstraintViolationException (@ExceptionHandler)",
    //            null,
    //            HttpStatus.BAD_REQUEST,
    //            request.getRequestURI(),
    //            ex.getLocalizedMessage(),
    //            errors
    //    );
    //
    //    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    //}
    //
    //@ExceptionHandler({ HttpMessageNotReadableException.class })
    //public ResponseEntity<Object> handleHttpMessageNotReadable(
    //        HttpMessageNotReadableException e,
    //        HttpServletRequest request
    //) {
    //    String error = "Request body inexistente o mal formado";
    //
    //    APIErrorEntity apiError = new APIErrorEntity(
    //            EXCEPTION_HANDLED_BY,
    //            "HttpMessageNotReadableException (@ExceptionHandler)",
    //            null,
    //            HttpStatus.BAD_REQUEST,
    //            request.getRequestURI(),
    //            e.getLocalizedMessage(),
    //            error
    //    );
    //
    //    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    //}
}
