package com.example.demo.models.productsdelivery;

import com.example.demo.models.vehicles.VehicleDTO;

public interface TransportMovementDTO {

    Long getTransportMovementId();
    HandlingEventDTO getHandlingEvents();
    DeliverySpecification getDeliverySpecification();
    DeliveryAddress getStartingAddress();
    VehicleDTO getVehicle();
}
