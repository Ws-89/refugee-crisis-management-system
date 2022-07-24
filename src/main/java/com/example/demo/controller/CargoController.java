package com.example.demo.controller;


import com.example.demo.models.HttpResponse;
import com.example.demo.models.cargo.Cargo;
import com.example.demo.models.products.Status;
import com.example.demo.service.CargoServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/cargo")
public class CargoController {

    private final CargoServiceImplementation cargoServiceImplementation;
    public CargoController(CargoServiceImplementation cargoServiceImplementation) {
        this.cargoServiceImplementation = cargoServiceImplementation;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HttpResponse> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("object", this.cargoServiceImplementation.findById(id)))
                        .message("Cargo retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<HttpResponse> findByDescriptionContaining(@RequestParam Optional<Status> status,
                                                                    @RequestParam Optional<String> description,
                                                                    @RequestParam Optional<Integer> page,
                                                                    @RequestParam Optional<Integer> size) {
        return ResponseEntity.ok().body(HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(Map.of("page", this.cargoServiceImplementation.findByStatusAndDescriptionContaining(
                                status.orElse(Status.Available),
                                description.orElse(""),
                                page.orElse(0),
                                size.orElse(10))))
                        .message("Cargo list retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping("/save")
    public ResponseEntity<HttpResponse> createCargo(@RequestBody Cargo cargo){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("created", this.cargoServiceImplementation.saveCargo(cargo)))
                        .message("Cargo created")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<HttpResponse> updateCargo(@RequestBody Cargo cargo){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("object", this.cargoServiceImplementation.updateCargo(cargo)))
                        .message("Cargo updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteCargo(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("deleted", this.cargoServiceImplementation.deleteCargo(id)))
                        .message("Cargo deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/{cargoId}/assign/{productId}")
    public ResponseEntity<HttpResponse> assignProductToCargo(@PathVariable("cargoId") Long cargoId, @PathVariable("productId") Long productId){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("assigned", this.cargoServiceImplementation.assignProductToCargo(cargoId, productId)))
                        .message("Assign product to cargo")
                        .status(HttpStatus.OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/{cargoId}/remove-from-cargo/{productId}")
    public ResponseEntity<HttpResponse> removeProductFromCargo(@PathVariable("cargoId") Long cargoId, @PathVariable("productId") Long productId){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("removed-from-cargo", this.cargoServiceImplementation.removeProductFromCargo(cargoId, productId)))
                        .message("Remove product from cargo")
                        .status(HttpStatus.OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/finish-cargo-completion/{cargoId}")
    public ResponseEntity<HttpResponse> finishCargoCompletion(@PathVariable("cargoId")Long cargoId){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("finished", this.cargoServiceImplementation.finishCargoCompletion(cargoId)))
                        .message("Cargo completion finshed")
                        .status(HttpStatus.OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

}
