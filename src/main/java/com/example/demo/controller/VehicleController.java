package com.example.demo.controller;

import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.models.vehicles.VehicleDTO;
import com.example.demo.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/save")
    public ResponseEntity<Vehicle> saveVehicle(@RequestBody Vehicle product) {
        return ResponseEntity.ok(vehicleService.saveVehicle(product));
    }

    @PutMapping("/update")
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody Vehicle product){
        return ResponseEntity.ok(vehicleService.updateVehicle(product));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteVehicle(@PathVariable("id") Long id){
        return ResponseEntity.ok(vehicleService.deleteVehicle(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<VehicleDTO>> findAllVehicles(){
        return ResponseEntity.ok(vehicleService.findAllVehicles());
//        return ResponseEntity.ok(vehicleService.findAllVehicles());
    }

    @GetMapping("/highest-capacity")
    public ResponseEntity<List<Double>> capacityList(){
        return ResponseEntity.ok(vehicleService.highestCapacity());
    }

//    @GetMapping("/list/vans")
//    public ResponseEntity<List<Vehicle>> findAllVans(){
//        return ResponseEntity.ok(vehicleService.findAllVans());
//    }
//
//    @GetMapping("/list/trucks")
//    public ResponseEntity<List<Vehicle>> findAllTrucks(){
//        return ResponseEntity.ok(vehicleService.findAllTrucks());
//    }
//
//    @GetMapping("/list/passenger-cars")
//    public ResponseEntity<List<Vehicle>> findAllPassengerCars(){
//        return ResponseEntity.ok(vehicleService.findAllPassengerCars());
//    }
}
