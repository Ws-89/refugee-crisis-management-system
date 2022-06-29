package com.example.demo.controller;

import com.example.demo.models.productsdelivery.HandlingEvent;
import com.example.demo.models.productsdelivery.HandlingEventDTO;
import com.example.demo.service.HandlingEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/handling-event")
public class HandlingEventsController {

    private final HandlingEventService handlingEventService;

    public HandlingEventsController(HandlingEventService handlingEventService) {
        this.handlingEventService = handlingEventService;
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<HandlingEvent> saveHandlingEvent(@RequestBody HandlingEventDTO handlingEventDTO, @PathVariable("id") Long id){
        return ResponseEntity.ok(handlingEventService.saveHandlingEvent(handlingEventDTO, 15L));
    }
}
