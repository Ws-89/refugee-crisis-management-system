package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.models.vehicles.VehicleDTO;
import com.example.demo.models.vehicles.VehicleFactoryImplementation;
import com.example.demo.repo.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImplementation implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImplementation(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Vehicle with id %s not found", id)));
    }


    @Override
    public Vehicle saveVehicle(VehicleDTO source) {
        Optional<Vehicle> ifExists = vehicleRepository.findByLicensePlate(source.getLicensePlate());
        if(ifExists.isPresent()) {
            throw new IllegalStateException(String.format("Vehicle with license plate %s", source.getLicensePlate()));
        }
        Vehicle vehicleToSave = VehicleFactoryImplementation.getInstance(source);
        return vehicleRepository.save(vehicleToSave);
    }

    @Override
    public Vehicle updateVehicle(VehicleDTO source) {
        Vehicle vehicleToUpdate = findById(source.getVehicleId());
        vehicleToUpdate.update(source);
        return vehicleRepository.save(vehicleToUpdate);
    }

    @Override
    public Long deleteVehicle(Long id) {
        Vehicle vehicleToDelete = findById(id);
        vehicleRepository.delete(vehicleToDelete);
        return vehicleToDelete.getVehicleId();
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> findAllTrucks() {
        return vehicleRepository.getTrucks();
    }

    @Override
    public List<Vehicle> findAllVans() {
        return vehicleRepository.getVans();
    }

    @Override
    public List<Vehicle> findAllPassengerCars() {
        return vehicleRepository.getPassengerCars();
    }
}
