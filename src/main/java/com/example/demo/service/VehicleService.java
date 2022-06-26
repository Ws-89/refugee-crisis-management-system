package com.example.demo.service;

import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.models.vehicles.VehicleDTO;

import java.util.List;

public interface VehicleService {

    public Vehicle findById(Long id);
    public Vehicle saveVehicle(VehicleDTO product);
    public Vehicle updateVehicle(VehicleDTO product);
    public Long deleteVehicle(Long id);
    public List<Vehicle> findAllVehicles();
//    public List<Vehicle> findAllTrucks();
//    public List<Vehicle> findAllVans();
//    public List<Vehicle> findAllPassengerCars();
}
