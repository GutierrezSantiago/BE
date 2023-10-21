package com.bda.carrental.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "client_companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCompany {

    @Id
    private long id;

    @Basic
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_contact")
    private String emailContact;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    public List<Client> clientList;

}
