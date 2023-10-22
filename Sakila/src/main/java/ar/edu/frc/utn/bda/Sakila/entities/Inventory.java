package ar.edu.frc.utn.bda.Sakila.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inventoryId;

    @ManyToOne
    @JoinColumn(name="film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;
}
