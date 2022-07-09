package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.vehicles.*;
import com.example.demo.repo.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void findHighestCapacity(){
        List<Vehicle> vehicles = Arrays.asList(Vehicle.builder().capacity(2.0).build(),
                Vehicle.builder().capacity(5.0).build(),
                Vehicle.builder().capacity(10.0).build(),
                Vehicle.builder().capacity(7.0).build(),
                Vehicle.builder().capacity(1.0).build());


        when(vehicleRepository.findAll()).thenReturn(vehicles);

        assertThat(vehicleServiceImplementation.highestCapacity()).isEqualTo(Arrays.asList(10.0, 7.0, 5.0, 2.0, 1.0));
    }

    @Test
    void findVehicleByIdShouldThrowException() {

        when(vehicleRepository.findById(1L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> vehicleServiceImplementation.findById(1L));


    }

    @Test
    void shouldFindVehicleById() {
        Vehicle vehicle = Vehicle.builder().vehicleId(1L).brand("Mercedes")
                .model("Vito").engine("2.0").capacity(750.0)
                .vehicleCategory("Van").licensePlate("cb-12345")
                .build();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        VehicleDTO result = vehicleServiceImplementation.findById(1L);

        VehicleDTO vehicleDTO = VehicleMapper.INSTANCE.entityToDTO(vehicle);

        assertThat(result.getCapacity()).isEqualTo(vehicleDTO.getCapacity());
        assertThat(result.getVehicleCategory()).isEqualTo(vehicleDTO.getVehicleCategory());
    }

    @Test
    void shouldSaveVehicle() {
        Vehicle vehicle = Vehicle.builder().vehicleId(1L).brand("Mercedes")
                .model("Vito").engine("2.0").capacity(750.0)
                .vehicleCategory("Van").licensePlate("cb-12345")
                .build();

        when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenReturn(vehicle);

        VehicleDTO vehicleDTO = vehicleServiceImplementation.saveVehicle(vehicle);

        assertThat(vehicleDTO.getVehicleId()).isEqualTo(vehicle.getVehicleId());
        assertThat(vehicleDTO.getBrand()).isEqualTo(vehicle.getBrand());
        assertThat(vehicleDTO.getModel()).isEqualTo(vehicle.getModel());
        assertThat(vehicleDTO.getEngine()).isEqualTo(vehicle.getEngine());
        assertThat(vehicleDTO.getCapacity()).isEqualTo(vehicle.getCapacity());
        assertThat(vehicleDTO.getVehicleCategory()).isEqualTo(vehicle.getVehicleCategory());
        assertThat(vehicleDTO.getLicensePlate()).isEqualTo(vehicle.getLicensePlate());
    }

    @Test
    void shouldUpdateVehicle() {
        Vehicle vehicle = Vehicle.builder().vehicleId(1L).brand("Mercedes")
                .model("Vito").engine("2.0").capacity(750.0)
                .vehicleCategory("Van").licensePlate("cb-12345")
                .build();

        Vehicle updatedVehicle = Vehicle.builder().vehicleId(1L).brand("Mercedes")
                .model("Vito").engine("2.0").capacity(850.0)
                .vehicleCategory("Van").licensePlate("cb-12345")
                .build();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        VehicleDTO vehicleDTO = vehicleServiceImplementation.updateVehicle(updatedVehicle);

        ArgumentCaptor<Vehicle> vehicleArgumentCaptor = ArgumentCaptor.forClass(Vehicle.class);
        verify(vehicleRepository).save(vehicleArgumentCaptor.capture());
        Vehicle vehicleArgumentCaptorValue = vehicleArgumentCaptor.getValue();


        assertThat(vehicleArgumentCaptorValue.getVehicleCategory()).isEqualTo(updatedVehicle.getVehicleCategory());
        assertThat(vehicleArgumentCaptorValue.getCapacity()).isEqualTo(updatedVehicle.getCapacity());
    }

    @Test
    void updateShouldThrowException(){
        Vehicle updatedVehicle = Vehicle.builder().vehicleId(1L).brand("Mercedes")
                .model("Vito").engine("2.0").capacity(850.0)
                .vehicleCategory("Van").licensePlate("cb-12345")
                .build();

        when(vehicleRepository.findById(1L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> vehicleServiceImplementation.updateVehicle(updatedVehicle));
    }

    @Test
    void shouldDeleteVehicle() {
        Vehicle vehicle = Vehicle.builder().vehicleId(1L).brand("Mercedes")
                .model("Vito").engine("2.0").capacity(750.0)
                .vehicleCategory("Van").licensePlate("cb-12345")
                .build();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.ofNullable(vehicle));
        vehicleServiceImplementation.deleteVehicle(1L);

        verify(vehicleRepository).delete(vehicle);
    }

    @Test
    void deleteShouldThrowException() {

        when(vehicleRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> vehicleServiceImplementation.deleteVehicle(1L));
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