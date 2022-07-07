package com.example.demo.dto;

import com.example.demo.models.products.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDeliveryDTO{
    private long deliveryId;
    private String description;
    private Double totalWeight;
    private Status status;
    private DeliveryAddressDTO startingAddress;
    private DeliverySpecificationDTO deliverySpecification;
    private List<ProductDTO> products;
}
