package ar.edu.frc.utn.bda.Sakila.repositories;

import ar.edu.frc.utn.bda.Sakila.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
