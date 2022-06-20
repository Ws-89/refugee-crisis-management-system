package com.example.demo.service;

import com.example.demo.models.products.FoodProduct;
import com.example.demo.models.vehicles.*;
import com.example.demo.repo.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplementationTest {

    @InjectMocks
    VehicleServiceImplementation vehicleServiceImplementation;

    @Mock
    VehicleRepository vehicleRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(vehicleRepository).isNotNull();

        assertThat(vehicleServiceImplementation).isNotNull();
    }

    @Test
    void findById() {
        Truck truck = new Truck(300.0, "123", TruckType.light);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(truck));
        Vehicle vehicle = vehicleServiceImplementation.findById(1L);
        Truck truckToCheck = (Truck)vehicle;

        assertThat(truck.getCapacity()).isEqualTo(truckToCheck.getCapacity());
        assertThat(truck.getTruckType()).isEqualTo(truckToCheck.getTruckType());

    }

    @Test
    void saveProduct() {
        VehicleDTO vehicleDTO = new VehicleDTO().builder()
                .vehicleType(VehicleType.Truck)
                .truckType(TruckType.light)
                .capacity(300.0)
                .licensePlate("123")
                .build();

        Truck truck = new Truck(300.0, "123", TruckType.light);

        when(vehicleRepository.findByLicensePlate("123")).thenReturn(Optional.ofNullable(null));
        vehicleServiceImplementation.saveVehicle(vehicleDTO);

        ArgumentCaptor<Truck> vehicleCaptor = ArgumentCaptor.forClass(Truck.class);
        verify(vehicleRepository).save(vehicleCaptor.capture());

        Truck truckVehicle = vehicleCaptor.getValue();

        assertThat(truckVehicle.getTruckType()).isEqualTo(vehicleDTO.getTruckType());
        assertThat(truckVehicle.getCapacity()).isEqualTo(vehicleDTO.getCapacity());
        assertThat(truckVehicle.getLicensePlate()).isEqualTo(vehicleDTO.getLicensePlate());
    }

    @Test
    void updateProduct() {
        VehicleDTO vehicleDTO = new VehicleDTO().builder()
                .vehicleId(1L)
                .vehicleType(VehicleType.Truck)
                .truckType(TruckType.light)
                .capacity(300.0)
                .licensePlate("123")
                .build();

        Truck truckToUpdate = new Truck(150.0, "123", TruckType.light);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.ofNullable(truckToUpdate));
        vehicleServiceImplementation.updateVehicle(vehicleDTO);

        ArgumentCaptor<Truck> vehicleCaptor = ArgumentCaptor.forClass(Truck.class);
        verify(vehicleRepository).save(vehicleCaptor.capture());

        Truck updatedTruck = vehicleCaptor.getValue();

        assertThat(updatedTruck.getTruckType()).isEqualTo(vehicleDTO.getTruckType());
        assertThat(updatedTruck.getCapacity()).isEqualTo(vehicleDTO.getCapacity());
    }

    @Test
    void deleteProduct() {
        Truck truck = new Truck(150.0, "123", TruckType.light);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.ofNullable(truck));
        vehicleServiceImplementation.deleteVehicle(1L);

        ArgumentCaptor<Truck> vehicleCaptor = ArgumentCaptor.forClass(Truck.class);
        verify(vehicleRepository).delete(vehicleCaptor.capture());

        Truck deletedTruck = vehicleCaptor.getValue();

        assertThat(deletedTruck.getTruckType()).isEqualTo(truck.getTruckType());
        assertThat(deletedTruck.getCapacity()).isEqualTo(truck.getCapacity());
    }

    @Test
    void findAllVehicles() {
    }

    @Test
    void findAllTrucks() {
    }

    @Test
    void findAllVans() {
    }

    @Test
    void findAllPassengerCars() {
    }
}