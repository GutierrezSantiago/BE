package ar.edu.frc.utn.bda.Sakila.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @ManyToOne
    @JoinColumn(name="address_id", nullable = false)
    private Address address;

    @Column(name="active")
    private char active;

    @Column(name="create_date")
    private Date createDate;
}
