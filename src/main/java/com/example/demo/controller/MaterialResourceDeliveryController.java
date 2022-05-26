package com.example.demo.controller;

import com.example.demo.service.MaterialResourceDeliveryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaterialResourceDeliveryController {

    private final MaterialResourceDeliveryService materialResourceDeliveryService;

    public MaterialResourceDeliveryController(MaterialResourceDeliveryService materialResourceDeliveryService) {
        this.materialResourceDeliveryService = materialResourceDeliveryService;
    }
}
