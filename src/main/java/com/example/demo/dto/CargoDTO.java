package com.example.demo.dto;

import com.example.demo.models.products.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoDTO {
    private long cargoId;
    private String description;
    private Double totalWeight;
    private Status status;
    private AddressDTO startingAddress;
    private DeliverySpecificationDTO deliverySpecification;
    private Set<ProductDTO> products;
}
