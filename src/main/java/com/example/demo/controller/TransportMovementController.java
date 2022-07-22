package com.example.demo.controller;

import com.example.demo.dto.TransportMovementDTO;
import com.example.demo.dto.TransportMovementFullGraphDTO;
import com.example.demo.models.cargo.TransportMovement;
import com.example.demo.requests.AssignPackageToTransportRequest;
import com.example.demo.service.TransportMovementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/transport-movement")
public class TransportMovementController {

    private final TransportMovementService transportMovementService;

    public TransportMovementController(TransportMovementService transportMovementService) {
        this.transportMovementService = transportMovementService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TransportMovementFullGraphDTO> findById(@PathVariable("id") Long id) {
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

    @PostMapping("/add-package-to-transport")
    public ResponseEntity<TransportMovementDTO> addPackageToTransport(@RequestBody AssignPackageToTransportRequest assignPackageToTransportRequest){
        return ResponseEntity.ok(this.transportMovementService.addAShipment(assignPackageToTransportRequest));
    }

    @DeleteMapping("/{transportId}/delete-package/{deliveryId}")
    public ResponseEntity<TransportMovementFullGraphDTO> delete(@PathVariable("transportId") Long transportId, @PathVariable("deliveryId") Long deliveryId){
        return ResponseEntity.ok(this.transportMovementService.removeAShipment(transportId, deliveryId));
    }

    @GetMapping("/{transportId}/change-route-order-up/{transportSpecificationId}")
    public ResponseEntity<TransportMovementFullGraphDTO> changeRouteOrderUp(
            @PathVariable("transportId") Long transportId, @PathVariable("transportSpecificationId")Long transportSpecificationId){
        return ResponseEntity.ok(this.transportMovementService.changeRouteOrderUp(transportId, transportSpecificationId));
    }

    @GetMapping("/{transportId}/change-route-order-down/{transportSpecificationId}")
    public ResponseEntity<TransportMovementFullGraphDTO> changeRouteOrderDown(
            @PathVariable("transportId") Long transportId, @PathVariable("transportSpecificationId")Long transportSpecificationId){
        return ResponseEntity.ok(this.transportMovementService.changeRouteOrderDown(transportId, transportSpecificationId));
    }

    @GetMapping("/{transportId}/generate-a-route")
    public ResponseEntity<TransportMovementFullGraphDTO> generateARoute(@PathVariable("transportId") Long transportId){
        return ResponseEntity.ok(this.transportMovementService.generateARoute(transportId));
    }

    @GetMapping("/{transportId}/preparationFinished")
    public ResponseEntity<TransportMovementFullGraphDTO> preparationFinished(@PathVariable("transportId") Long transportId){
        return ResponseEntity.ok(this.transportMovementService.preparationFinished(transportId));
    }

    @GetMapping("/find-transport-that-stops-at-address/{addressId}")
    public ResponseEntity<List<TransportMovementFullGraphDTO>> findAllWithProducts(@PathVariable("addressId")Long addressId){
        return ResponseEntity.ok(this.transportMovementService.findTransportMovementsThatStopsAtAddress(addressId));
    }

    @GetMapping("/{transportId}/finish-transport")
    public ResponseEntity<String> finishTransport(@PathVariable("transportId")Long transportId){
        this.transportMovementService.finishTransport(transportId);
        return ResponseEntity.ok("OK");
    }

}
