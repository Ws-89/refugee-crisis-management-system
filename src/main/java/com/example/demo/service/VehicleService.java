package com.example.demo.service;

import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.models.vehicles.VehicleDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VehicleService {

    public VehicleDTO findById(Long id);
    public VehicleDTO saveVehicle(Vehicle product);
    public VehicleDTO updateVehicle(Vehicle product);
    public List<VehicleDTO> findAllVehiclesWithoutTransportMovement();
    public Long deleteVehicle(Long id);
    public Page<VehicleDTO> findByCapacityGreaterThanEqual(Double capacity, int page, int size);
    public List<Double> highestCapacity();
}
