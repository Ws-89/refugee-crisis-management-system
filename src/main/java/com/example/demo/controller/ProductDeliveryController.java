package com.example.demo.controller;

import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.service.ProductDeliveryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class ProductDeliveryController {

    private final ProductDeliveryService productDeliveryService;

    public ProductDeliveryController(ProductDeliveryService productDeliveryService) {
        this.productDeliveryService = productDeliveryService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDelivery> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.productDeliveryService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ProductDelivery>> findAllDelivery(@RequestBody Pageable page) {
        return ResponseEntity.ok(this.productDeliveryService.getPage(page));
    }

    @PostMapping("/save")
    public ResponseEntity<ProductDelivery> createDelivery(@RequestBody ProductDelivery productDelivery){
        return ResponseEntity.ok(this.productDeliveryService.create(productDelivery));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDelivery> updateDelivery(@PathVariable("id") Long id, @RequestBody ProductDelivery productDelivery){
        ProductDelivery delivery = this.productDeliveryService.get(id);
        delivery.update(productDelivery);
        return ResponseEntity.ok(this.productDeliveryService.update(delivery));
    }

    @DeleteMapping("/save")
    public ResponseEntity<String> deleteDelivery(@PathVariable Long id){
        this.productDeliveryService.delete(id);
        return ResponseEntity.ok("OK");
    }

}
