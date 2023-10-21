package com.bda.carrental.entidades.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCompanyDto {

    private long id;

    private String name;

    private String phoneNumber;

    private String emailContact;
}
