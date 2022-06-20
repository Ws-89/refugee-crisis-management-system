package com.example.demo.models.vehicles;

import org.springframework.stereotype.Service;

@Service
public class VehicleFactoryImplementation {

    public static Vehicle getInstance(VehicleDTO source){
        Vehicle vehicle = null;
        switch(source.getVehicleType()){
            case Van:
                vehicle = new Van(
                        source.getCapacity(),
                        source.getLicensePlate(),
                        source.getVanType()
                );
                break;
            case Truck:
                vehicle = new Truck(
                        source.getCapacity(),
                        source.getLicensePlate(),
                        source.getTruckType()
                );
                break;
            case PassengerCar:
                vehicle = new PassengerCar(
                        source.getCapacity(),
                        source.getLicensePlate(),
                        source.getPassengerCarType()
                );
                break;
        }
        return vehicle;
    }
}
