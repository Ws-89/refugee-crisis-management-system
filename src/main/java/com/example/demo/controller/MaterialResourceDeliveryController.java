package com.example.demo.controller;

import com.example.demo.models.materialResourceDelivery.MaterialResourceDelivery;
import com.example.demo.service.MaterialResourceDeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class MaterialResourceDeliveryController {

    private final MaterialResourceDeliveryService materialResourceDeliveryService;

    public MaterialResourceDeliveryController(MaterialResourceDeliveryService materialResourceDeliveryService) {
        this.materialResourceDeliveryService = materialResourceDeliveryService;
    }

    @GetMapping("/list")
    public List<MaterialResourceDelivery> findAllDelivery() {
        return this.materialResourceDeliveryService.findAllMaterialResourceDelivery();
    }

    @PostMapping("/save")
    public MaterialResourceDelivery createDelivery(@RequestBody MaterialResourceDelivery materialResourceDelivery){
        return this.materialResourceDeliveryService.sendDelivery(materialResourceDelivery);
    }

    @PostMapping("/next-state/{id}")
    public MaterialResourceDelivery nextState(@PathVariable("id") Long id){
        return this.materialResourceDeliveryService.nextState(id);
    }

    @PostMapping("/prev-state/{id}")
    public MaterialResourceDelivery prevState(@PathVariable("id") Long id){
        return this.materialResourceDeliveryService.prevState(id);
    }
}
