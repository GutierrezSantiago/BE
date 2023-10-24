package ar.edu.utn.frc.bso.servicioAlumnos.controllers;

import ar.edu.utn.frc.bso.servicioAlumnos.exceptions.InvalidDataException;
import ar.edu.utn.frc.bso.servicioAlumnos.model.Alumno;
import ar.edu.utn.frc.bso.servicioAlumnos.services.AlumnoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnosController {

    private AlumnoService alumnoService;

    @Autowired
    public AlumnosController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public List<Alumno> getAlumnos() {
        return alumnoService.getAllAlumnos();
    }

    @GetMapping("/id/{legajo}")
    public ResponseEntity<Alumno> getAlumno(@PathVariable int legajo) {
        Optional<Alumno> alumno = alumnoService.getAlumno(legajo);
        if (alumno.isPresent()) {
            return new ResponseEntity<>(alumno.get(), HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping()
    public ResponseEntity<Void> newAlumno(@RequestBody Alumno a) {
        try {
            alumnoService.addAlumno(a);
        } catch (InvalidDataException e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

}
