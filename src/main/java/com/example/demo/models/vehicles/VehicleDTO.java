package com.example.demo.models.vehicles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public record VehicleDTO (
     Long vehicleId,
     String brand,
     String model,
     String engine,
     Double capacity,
     String vehicleCategory,
     String licensePlate
){}




