package ar.edu.frc.utn.bda.Sakila.repositories;

import ar.edu.frc.utn.bda.Sakila.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
}
