package ar.edu.utn.frc.bso.servicioAlumnos.controllers;

import ar.edu.utn.frc.bso.servicioAlumnos.entities.Alumno;
import ar.edu.utn.frc.bso.servicioAlumnos.services.AlumnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlumnosController {


    private AlumnosService service;

    @Autowired
    public AlumnosController(AlumnosService service) {
        this.service = service;
    }

    @GetMapping("/alumnos/")
    public List<Alumno> getAllAlumnos() {
        return service.getAllAlumnos();
    }

    @GetMapping("/alumnos/regulares")
    public List<Alumno> getAlumnosRegulares() {
        return service.getAlumnosRegulares();
    }

}
