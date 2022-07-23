package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportMovementSpecificationDTO {

    private Long transportMovementSpecificationId;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private AddressDTO deliveryAddress;
}
