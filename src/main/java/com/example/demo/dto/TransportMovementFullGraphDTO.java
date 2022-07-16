package com.example.demo.dto;

import com.example.demo.models.productsdelivery.DeliveryHistory;
import com.example.demo.models.productsdelivery.TransportMovementSpecification;
import com.example.demo.models.vehicles.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportMovementFullGraphDTO {

    Long transportMovementId;
    DeliveryAddressDTO startingAddress;
    DeliveryAddressDTO deliveryAddress;
    Double weightOfTheGoods;
    VehicleDTO vehicle;
    List<DeliveryHistoryDTO> wayBills;
    List<TransportMovementSpecificationDTO> transportMovementSpecifications;
}
