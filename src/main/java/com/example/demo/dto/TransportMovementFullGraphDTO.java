package com.example.demo.dto;

import com.example.demo.models.cargo.TransportStatus;
import com.example.demo.models.vehicles.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportMovementFullGraphDTO {

    Long transportMovementId;
    AddressDTO startingAddress;
    AddressDTO deliveryAddress;
    Double weightOfTheGoods;
    LocalDateTime arrivalTime;
    LocalDateTime departureTime;
    VehicleDTO vehicle;
    Set<DeliveryHistoryDTO> wayBills;
    List<TransportMovementSpecificationDTO> transportMovementSpecifications;
    TransportStatus transportStatus;
}
