package com.example.demo.controller;

import com.example.demo.models.productsdelivery.DeliveryAddress;
import com.example.demo.service.DeliveryAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/delivery-address")
public class DeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;

    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @PostMapping("/save")
    public ResponseEntity<DeliveryAddress> save(@RequestBody DeliveryAddress deliveryAddress){
        return ResponseEntity.ok(deliveryAddressService.save(deliveryAddress));
    }

    @PostMapping("/update")
    public ResponseEntity<DeliveryAddress> update(@RequestBody DeliveryAddress deliveryAddress){
        return ResponseEntity.ok(deliveryAddressService.update(deliveryAddress));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(deliveryAddressService.delete(id));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DeliveryAddress> findById(@PathVariable("id")Long id){
        return ResponseEntity.ok(deliveryAddressService.findById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<DeliveryAddress>> findAll(){
        return ResponseEntity.ok(deliveryAddressService.findAll());
    }
}
