package com.example.demo.models.vehicles;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue(value = "TRUCK")
@Data
@NoArgsConstructor
public class Truck extends Vehicle {

    @Enumerated(value = EnumType.STRING)
    private TruckType truckType;

    public Truck(Double capacity, String licensePlate, TruckType truckType) {
        super(capacity, licensePlate);
        this.truckType = truckType;
    }

    @Override
    public void update(VehicleDTO vehicle) {
        if(vehicle.getTruckType() != null){
            super.setCapacity(vehicle.getCapacity());
            super.setLicensePlate(vehicle.getLicensePlate());
            this.truckType = vehicle.getTruckType();
        }
    }
}
