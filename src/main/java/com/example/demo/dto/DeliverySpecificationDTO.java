package com.example.demo.dto;

import com.example.demo.models.productsdelivery.DeliveryAddress;

import java.time.LocalDateTime;

public record DeliverySpecificationDTO(Long deliverySpecificationId,
                                       LocalDateTime arrivalTime,
                                       DeliveryAddress deliveryAddress) {
}
