package com.example.demo.models.vehicles;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehicleFactoryImplementationTest {

    @Test
    void getTruckInstance() {
        VehicleDTO vehicleDTO = VehicleDTO.builder()
                .licensePlate("123")
                .vehicleType(VehicleType.Truck)
                .capacity(300.0)
                .truckType(TruckType.light)
                .build();

        Vehicle vehicle = VehicleFactoryImplementation.getInstance(vehicleDTO);

        assertThat(vehicle).isInstanceOf(Truck.class);
    }

    @Test
    void getVanInstance() {
        VehicleDTO vehicleDTO = VehicleDTO.builder()
                .licensePlate("123")
                .vehicleType(VehicleType.Van)
                .capacity(300.0)
                .vanType(VanType.Large)
                .build();

        Vehicle vehicle = VehicleFactoryImplementation.getInstance(vehicleDTO);

        assertThat(vehicle).isInstanceOf(Van.class);
    }

    @Test
    void getPassengerCarInstance() {
        VehicleDTO vehicleDTO = VehicleDTO.builder()
                .licensePlate("123")
                .vehicleType(VehicleType.PassengerCar)
                .capacity(300.0)
                .passengerCarType(PassengerCarType.Coupe)
                .build();

        Vehicle vehicle = VehicleFactoryImplementation.getInstance(vehicleDTO);

        assertThat(vehicle).isInstanceOf(PassengerCar.class);
    }
}