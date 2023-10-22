package ar.edu.frc.utn.bda.Sakila.services.mappers;

import ar.edu.frc.utn.bda.Sakila.entities.Film;
import ar.edu.frc.utn.bda.Sakila.entities.dto.FilmDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class FilmDtoMapper implements Function<Film, FilmDto> {
    @Override
    public FilmDto apply(Film film) {
        return  new FilmDto(
                film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguageId(),
                film.getOriginalLanguageId(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
                film.getSpecialFeatures());
    }

}
