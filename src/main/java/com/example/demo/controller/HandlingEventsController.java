package com.example.demo.controller;

import com.example.demo.models.productsdelivery.HandlingEvent;
import com.example.demo.service.HandlingEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/handling-events")
public class HandlingEventsController {

    private final HandlingEventService handlingEventService;

    public HandlingEventsController(HandlingEventService handlingEventService) {
        this.handlingEventService = handlingEventService;
    }

    @PostMapping("/save/{deliveryId}/{transportId}")
    public ResponseEntity<HandlingEvent> saveHandlingEvent(@RequestBody HandlingEvent handlingEvent,
                                                           @PathVariable("deliveryId") Long deliveryId, @PathVariable("transportId") Long transportId){
        return ResponseEntity.ok(handlingEventService.saveHandlingEvent(handlingEvent, deliveryId, transportId));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HandlingEvent> findOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(handlingEventService.getHandlingEvent(id));
    }

    @GetMapping("/list-of-transport-movement/{id}")
    public ResponseEntity<List<HandlingEvent>> findAllByTransportMovement(@PathVariable("id") Long id){
        return ResponseEntity.ok(handlingEventService.findAllByTransportMovementId(id));
    }

    @PutMapping("/update")
    public ResponseEntity<HandlingEvent> updateHandlingEvent(@RequestBody HandlingEvent handlingEvent){
        return ResponseEntity.ok(handlingEventService.updateHandlingEvent(handlingEvent));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteHandlingEvent(@PathVariable("id") Long id){
        return ResponseEntity.ok(handlingEventService.removeHandlingEvent(id));
    }
}
