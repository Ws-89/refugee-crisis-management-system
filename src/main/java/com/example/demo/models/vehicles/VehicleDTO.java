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
    private String brand;
    private String model;
    private String engine;
    private Double capacity;
    private String licensePlate;
    private String vehicleCategory;
}
