package com.example.demo.dto;

import com.example.demo.models.products.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDeliveryTransportMovementDTO {
    private long deliveryId;
    private String description;
    private Double totalWeight;
    private Status status;
    private DeliveryAddressDTO startingAddress;
    private DeliverySpecificationDTO deliverySpecification;
}
