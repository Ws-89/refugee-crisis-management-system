package com.example.demo.service;

import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.models.vehicles.VehicleDTO;

import java.util.List;

public interface VehicleService {

    public Vehicle findById(Long id);
    public Vehicle saveVehicle(Vehicle product);
    public Vehicle updateVehicle(Vehicle product);
    public List<VehicleDTO> findAllVehiclesWithoutTransportMovement();
    public Long deleteVehicle(Long id);
    public List<Vehicle> findAllVehicles();
    public List<Double> highestCapacity();
}
