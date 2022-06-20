package com.example.demo.models.vehicles;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue(value = "VAN")
@Data
@NoArgsConstructor
public class Van extends Vehicle {

    @Enumerated(value = EnumType.STRING)
    private VanType vanType;

    public Van(Double capacity, String licensePlate, VanType vanType){
        super(capacity, licensePlate);
        this.vanType = vanType;
    }

    @Override
    public void update(VehicleDTO vehicle) {
        if(vehicle.getVanType() != null){
            super.setCapacity(vehicle.getCapacity());
            super.setLicensePlate(vehicle.getLicensePlate());
            this.vanType = vehicle.getVanType();
        }
    }
}
