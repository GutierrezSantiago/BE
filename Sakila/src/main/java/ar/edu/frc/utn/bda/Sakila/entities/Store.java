package ar.edu.frc.utn.bda.Sakila.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="store")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long storeId;

    @Column(name="manager_staff_id")
    private short managerStaffId;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Customer> customers;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "inventory", fetch = FetchType.LAZY)
    private List<Inventory> inventories;

}
