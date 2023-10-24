package ar.edu.utn.frc.bso.servicioAlumnos.controllers;

import ar.edu.utn.frc.bso.servicioAlumnos.entities.Alumno;
import ar.edu.utn.frc.bso.servicioAlumnos.entities.AuthResult;
import ar.edu.utn.frc.bso.servicioAlumnos.services.AlumnosService;
import ar.edu.utn.frc.bso.servicioAlumnos.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class AlumnosController {

    private AuthService authService;
    private AlumnosService service;

    @Autowired
    public AlumnosController(AuthService authService, AlumnosService service) {
        this.authService = authService;
        this.service = service;
    }

    @GetMapping("/alumnos/")
    public ResponseEntity<List<Alumno>> getAllAlumnos(@RequestHeader(name="Authorization", required = false) String auth) {
        AuthResult authResult = authService.validateAuth(auth, "viewer");
        if (authResult == AuthResult.INVALID_USER) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        } else if (authResult == AuthResult.FORBIDDEN) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(403));
        }
        List<Alumno> allAlumnos = service.getAllAlumnos();
        return new ResponseEntity<>(allAlumnos, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/alumnos/regulares")
    public ResponseEntity<List<Alumno>> getAlumnosRegulares(@RequestHeader(name="Authorization", required = false) String auth) {
        AuthResult authResult = authService.validateAuth(auth, "admin");
        if (authResult == AuthResult.INVALID_USER) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (authResult == AuthResult.FORBIDDEN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(service.getAlumnosRegulares(), HttpStatus.OK);
    }

}
