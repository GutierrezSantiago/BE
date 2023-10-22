package ar.edu.frc.utn.bda.Sakila.services.mappers;

import ar.edu.frc.utn.bda.Sakila.entities.Film;
import ar.edu.frc.utn.bda.Sakila.entities.Inventory;
import ar.edu.frc.utn.bda.Sakila.entities.dto.FilmDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
@Service
public class FilmMapper implements Function<FilmDto, Film> {
    @Override
    public Film apply(FilmDto filmDto) {
        return  new Film(
                filmDto.getFilmId(),
                filmDto.getTitle(),
                filmDto.getDescription(),
                filmDto.getReleaseYear(),
                filmDto.getLanguageId(),
                filmDto.getOriginalLanguageId(),
                filmDto.getRentalDuration(),
                filmDto.getRentalRate(),
                filmDto.getLength(),
                filmDto.getReplacementCost(),
                filmDto.getRating(),
                filmDto.getSpecialFeatures(),
                new ArrayList<>());
    }
}
