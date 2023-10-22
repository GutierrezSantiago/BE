package ar.edu.frc.utn.bda.Sakila.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    @Column(name="address")
    private String address;

    @Column(name="postal_code")
    private String postalCode;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Customer> customers;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Store> stores;


    // LAS FK LOS MAPEAMOS?
}
