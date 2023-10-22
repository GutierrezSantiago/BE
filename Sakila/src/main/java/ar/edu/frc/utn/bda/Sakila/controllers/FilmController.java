package ar.edu.frc.utn.bda.Sakila.controllers;

import ar.edu.frc.utn.bda.Sakila.entities.Film;
import ar.edu.frc.utn.bda.Sakila.entities.dto.FilmDto;
import ar.edu.frc.utn.bda.Sakila.services.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/film")
public class FilmController {
    private final FilmService filmService;
    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<List<FilmDto>> findAll(){
        List<FilmDto> values = this.filmService.findAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDto> findById(@PathVariable long id){
        FilmDto value = this.filmService.getById(id);
        return ResponseEntity.ok(value);
    }
}
