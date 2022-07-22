package com.example.demo.controller;

import com.example.demo.dto.CargoDTO;
import com.example.demo.models.HttpResponse;
import com.example.demo.models.cargo.Cargo;
import com.example.demo.service.CargoServiceImplementation;
import com.google.gson.Gson;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/cargo")
public class CargoController {

    private final CargoServiceImplementation cargoServiceImplementation;
    private static final Gson gson = new Gson();
    public CargoController(CargoServiceImplementation cargoServiceImplementation) {
        this.cargoServiceImplementation = cargoServiceImplementation;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CargoDTO> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cargoServiceImplementation.findById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<HttpResponse> findByDescriptionContaining(@RequestParam Optional<String> description, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return ResponseEntity.ok().body(HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(Map.of("page", this.cargoServiceImplementation.findByDescriptionContaining(description.orElse(""), page.orElse(0), size.orElse(10))))
                        .message("Cargo list retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping("/save")
    public ResponseEntity<CargoDTO> createDelivery(@RequestBody Cargo cargo){
        return ResponseEntity.ok(this.cargoServiceImplementation.saveCargo(cargo));
    }

    @PutMapping("/update")
    public ResponseEntity<CargoDTO> updateDelivery(@RequestBody Cargo cargo){
        return ResponseEntity.ok(this.cargoServiceImplementation.updateCargo(cargo));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteDelivery(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.cargoServiceImplementation.deleteCargo(id));
    }

    @GetMapping("/{cargoId}/assign/{productId}")
    public ResponseEntity<String> assignProductToDelivery(@PathVariable("cargoId") Long cargoId, @PathVariable("productId") Long productId){
        this.cargoServiceImplementation.assignProductToCargo(cargoId, productId);
        return ResponseEntity.ok(gson.toJson("OK"));
    }

    @GetMapping("/{cargoId}/remove-from-package/{productId}")
    public ResponseEntity<String> removeProductFromPackage(@PathVariable("cargoId") Long cargoId, @PathVariable("productId") Long productId){
        this.cargoServiceImplementation.removeProductFromCargo(cargoId, productId);
        return ResponseEntity.ok(gson.toJson("OK"));
    }

    @GetMapping("/finish-cargo-completion/{cargoId}")
    public ResponseEntity<String> finishCargoCompletion(@PathVariable("cargoId")Long cargoId){
        this.cargoServiceImplementation.finishCargoCompletion(cargoId);
        return ResponseEntity.ok(gson.toJson("OK"));
    }

}
