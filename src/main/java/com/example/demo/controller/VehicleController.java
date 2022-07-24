package com.example.demo.controller;

import com.example.demo.models.HttpResponse;
import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.models.vehicles.VehicleDTO;
import com.example.demo.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/get/{vehicleId}")
    public ResponseEntity<HttpResponse> updateVehicle(@PathVariable("vehicleId") Long vehicleId){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("object", vehicleService.findById(vehicleId)))
                        .message("Vehicle retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<HttpResponse> saveVehicle(@RequestBody Vehicle product) {
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("created", vehicleService.saveVehicle(product)))
                        .message("Vehicle created")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<HttpResponse> updateVehicle(@RequestBody Vehicle product){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("object", vehicleService.updateVehicle(product)))
                        .message("Vehicle updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteVehicle(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("deleted", vehicleService.deleteVehicle(id)))
                        .message("Vehicle deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<HttpResponse> findByCapacityGreaterThanEqual(@RequestParam Optional<Double> capacity,
                                                                       @RequestParam Optional<Integer> page,
                                                                       @RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("page", this.vehicleService.findByCapacityGreaterThanEqual(
                                capacity.orElse(0.0),
                                page.orElse(0),
                                size.orElse(10) )))
                        .message("Vehicle list retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
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
