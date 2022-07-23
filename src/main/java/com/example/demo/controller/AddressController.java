package com.example.demo.controller;

import com.example.demo.dto.AddressDTO;
import com.example.demo.models.HttpResponse;
import com.example.demo.models.cargo.Address;
import com.example.demo.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.*;
import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/save")
    public ResponseEntity<HttpResponse> save(@RequestBody Address address){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("created", addressService.save(address)))
                        .message("Address created")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<HttpResponse> update(@RequestBody Address address){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("object", addressService.update(address)))
                        .message("Address updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("deleted", addressService.delete(id)))
                        .message("Address deletd")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HttpResponse> findById(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("object", addressService.findById(id)))
                        .message("Address retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<HttpResponse> findByCityContaining(@RequestParam Optional<String> city,
                                                             @RequestParam Optional<Integer> page,
                                                             @RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("page", addressService.findByCityContaining(
                                city.orElse(""),
                                page.orElse(0),
                                size.orElse(10))))
                        .message("Address list retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
