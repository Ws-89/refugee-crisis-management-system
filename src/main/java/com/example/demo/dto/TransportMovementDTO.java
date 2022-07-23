package com.example.demo.dto;

import com.example.demo.models.cargo.TransportStatus;
import com.example.demo.models.vehicles.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportMovementDTO {

    Long transportMovementId;
    AddressDTO startingAddress;
    AddressDTO deliveryAddress;
    Double weightOfTheGoods;
    LocalDateTime arrivalTime;
    LocalDateTime departureTime;
    TransportStatus transportStatus;
    VehicleDTO vehicle;

}
