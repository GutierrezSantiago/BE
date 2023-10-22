package ar.edu.frc.utn.bda.Sakila.services;

import ar.edu.frc.utn.bda.Sakila.entities.Film;
import ar.edu.frc.utn.bda.Sakila.entities.dto.FilmDto;
import ar.edu.frc.utn.bda.Sakila.repositories.FilmRepository;
import ar.edu.frc.utn.bda.Sakila.services.mappers.FilmDtoMapper;
import ar.edu.frc.utn.bda.Sakila.services.mappers.FilmMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FilmServiceImpl implements FilmService{

    private final FilmRepository filmRepository;
    private final FilmDtoMapper dtoMapper;
    private final FilmMapper entityMapper;

    public FilmServiceImpl(FilmRepository filmRepository, FilmDtoMapper dtoMapper, FilmMapper entityMapper){
        this.filmRepository = filmRepository;
        this.dtoMapper = dtoMapper;
        this.entityMapper = entityMapper;
    }
    @Override
    public FilmDto add(FilmDto entity) {
        Optional<Film> film = Stream.of(entity).map(entityMapper).findFirst();
        try {

            this.filmRepository.save(film.get());


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return film.map(dtoMapper).orElseThrow();
    }

    @Override
    public FilmDto update(FilmDto entity) {
        Optional<Film> film = Stream.of(entity).map(entityMapper).findFirst();
        film.ifPresent(filmRepository::save);
        return film.map(dtoMapper).orElseThrow();
    }

    @Override
    public FilmDto delete(Long id) {
        FilmDto film = this.getById(id);
        if (film != null) {
            Optional<Film> entity = Stream.of(film).map(entityMapper).findFirst();
            entity.ifPresent(filmRepository::delete);
        }
        return film;
    }

    @Override
    public FilmDto getById(Long id) {
        Optional<Film> film = this.filmRepository.findById(id);
        return film.map(dtoMapper).orElseThrow();
    }

    @Override
    public List<FilmDto> findAll() {
        List<Film> films = this.filmRepository.findAll();
        return films
                .stream()
                .map(dtoMapper)
                .toList();
    }
}
