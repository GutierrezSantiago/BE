package ar.edu.frc.utn.bda.Sakila.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name="film")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long filmId;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="release_year")
    private String releaseYear;

    @Column(name="language_id", nullable = true)
    private Short languageId;

    @Column(name="original_language_id")
    private Short originalLanguageId;

    @Column(name="rental_duration")
    private short rentalDuration;

    @Column(name="rental_rate")
    private double rentalRate;

    @Column(name="length")
    private short length;

    @Column(name="replacement_cost")
    private double replacementCost;

    @Column(name="rating")
    private String rating;

    @Column(name="special_features")
    private String specialFeatures;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<Inventory> inventories;
}
