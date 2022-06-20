package com.example.demo.models.vehicles;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue(value = "PASSENGER")
@Data
@NoArgsConstructor
public class PassengerCar extends Vehicle {

    @Enumerated(value = EnumType.STRING)
    private PassengerCarType passengerCarType;

    public PassengerCar(Double capacity, String licensePlate, PassengerCarType passengerCarType) {
        super(capacity, licensePlate);
        this.passengerCarType = passengerCarType;
    }

    @Override
    public void update(VehicleDTO vehicle) {
        if(vehicle.getVehicleType() != null){
            super.setCapacity(vehicle.getCapacity());
            super.setLicensePlate(vehicle.getLicensePlate());
            this.passengerCarType = vehicle.getPassengerCarType();
        }
    }
}
