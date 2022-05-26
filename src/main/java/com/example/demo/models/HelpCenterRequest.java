package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelpCenterRequest {

    private String street;
    private String city;
    private String country;
    private String telephoneNumber;
    private String email;

}
