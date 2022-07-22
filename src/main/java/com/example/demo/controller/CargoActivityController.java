package com.example.demo.controller;

import com.example.demo.models.cargo.CargoActivity;
import com.example.demo.service.CargoActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cargo-activity")
public class CargoActivityController {

    private CargoActivityService cargoActivityService;

    public CargoActivityController(CargoActivityService cargoActivityService) {
        this.cargoActivityService = cargoActivityService;
    }

    @PostMapping("/{deliveryHistoryId}/save")
    public ResponseEntity<Long> saveCargoActivity(@PathVariable("deliveryHistoryId") Long deliveryHistoryId, @RequestBody CargoActivity cargoActivity){
        return ResponseEntity.ok(this.cargoActivityService.addTransportActivity(deliveryHistoryId, cargoActivity));
    }



}
