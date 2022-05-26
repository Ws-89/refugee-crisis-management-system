package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefugeeRegistrationRequest {

    private String name;
    private String surname;
    private String nationality;
    private int age;
    private String street;
    private String city;
    private String country;
    private String telephoneNumber;
    private String email;

}
