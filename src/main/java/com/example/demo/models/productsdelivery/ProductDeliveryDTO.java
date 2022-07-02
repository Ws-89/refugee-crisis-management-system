package com.example.demo.models.productsdelivery;

import com.example.demo.models.products.ProductDTO;
import com.example.demo.models.products.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ProductDeliveryDTO {

    private Long deliveryId;
    private DeliveryHistory deliveryHistory;
    private DeliverySpecification deliverySpecification;
    private String description;
    private Set<ProductDTO> products = new HashSet<>();
    private DeliveryAddress startingAddress;
    private Double totalWeight;
    private Status status;
}
