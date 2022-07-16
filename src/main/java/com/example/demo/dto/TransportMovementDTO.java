package com.example.demo.dto;

import com.example.demo.models.productsdelivery.DeliveryHistory;
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
    DeliveryAddressDTO startingAddress;
    DeliveryAddressDTO deliveryAddress;
    Double weightOfTheGoods;
    VehicleDTO vehicle;

}
