package com.example.demo.controller;

import com.example.demo.models.productsdelivery.DeliverySpecification;
import com.example.demo.repo.DeliverySpecificationRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/delivery-specification")
public class DeliverySpecificationController {

    private final DeliverySpecificationRepo deliverySpecificationRepo;

    public DeliverySpecificationController(DeliverySpecificationRepo deliverySpecificationRepo) {
        this.deliverySpecificationRepo = deliverySpecificationRepo;
    }

    @PostMapping("/save")
    public DeliverySpecification save(@RequestBody DeliverySpecification deliverySpecification){
        return this.deliverySpecificationRepo.save(deliverySpecification);
    }
}
