package com.example.demo.dto;

import com.example.demo.models.productsdelivery.DeliverySpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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
