package com.example.demo.repo;

import com.example.demo.models.vehicles.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

//    @Query("from PassengerCar")
//    public List<Vehicle> getPassengerCars();
//    @Query("from Van")
//    public List<Vehicle> getVans();
//    @Query("from Truck")
//    public List<Vehicle> getTrucks();

    public Optional<Vehicle> findByLicensePlate(String licensePlate);
}
