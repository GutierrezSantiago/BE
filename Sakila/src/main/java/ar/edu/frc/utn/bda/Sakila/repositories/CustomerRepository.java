package ar.edu.frc.utn.bda.Sakila.repositories;

import ar.edu.frc.utn.bda.Sakila.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
