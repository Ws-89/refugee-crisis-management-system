package com.example.demo.controller;

import com.example.demo.dto.TransportMovementDTO;
import com.example.demo.models.productsdelivery.TransportMovement;
import com.example.demo.service.TransportMovementService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/transport-movement")
public class TransportMovementController {

    private final TransportMovementService transportMovementService;

    public TransportMovementController(TransportMovementService transportMovementService) {
        this.transportMovementService = transportMovementService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TransportMovementDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.transportMovementService.findById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<TransportMovementDTO>> findAll(){
        return ResponseEntity.ok(this.transportMovementService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<TransportMovementDTO> save(@RequestBody TransportMovement transportMovement){
        return ResponseEntity.ok(this.transportMovementService.save(transportMovement));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.transportMovementService.delete(id));
    }

    @PutMapping("/update")
    public ResponseEntity<TransportMovementDTO> update(@RequestBody TransportMovement transportMovement){
        return ResponseEntity.ok(this.transportMovementService.update(transportMovement));
    }

}
