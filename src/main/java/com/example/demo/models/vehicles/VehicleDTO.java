package com.example.demo.models.vehicles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private Long vehicleId;
    private Double capacity;
    private VehicleType vehicleType;
    private PassengerCarType passengerCarType;
    private VanType vanType;
    private TruckType truckType;
    private String licensePlate;
}
