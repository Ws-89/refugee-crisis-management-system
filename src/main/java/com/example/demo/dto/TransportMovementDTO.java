package com.example.demo.dto;

import com.example.demo.models.vehicles.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportMovementDTO {

    Long transportMovementId;
    DeliverySpecificationDTO deliverySpecification;
    DeliveryAddressDTO startingAddress;
    VehicleDTO vehicle;
    List<HandlingEventDTO> handlingEvents;

}
