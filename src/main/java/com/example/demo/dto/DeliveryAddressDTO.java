package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressDTO {
    private Long deliveryAddressId;
    private String postCode;
    private String city;
    private String street;
    private String state;
}
