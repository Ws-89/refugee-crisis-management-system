package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliverySpecificationDTO {
    private Long deliverySpecificationId;
    private LocalDateTime arrivalTime;
    private DeliveryAddressDTO deliveryAddress;

}
