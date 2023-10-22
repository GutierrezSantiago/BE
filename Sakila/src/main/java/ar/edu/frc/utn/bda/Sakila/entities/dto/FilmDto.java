package ar.edu.frc.utn.bda.Sakila.entities.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {
    private long filmId;

    private String title;

    private String description;

    private String releaseYear;

    private Short languageId;

    private Short originalLanguageId;

    private short rentalDuration;

    private double rentalRate;

    private short length;

    private double replacementCost;

    private String rating;

    private String specialFeatures;
}
