package com.example.demo.controller;

import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.models.productsdelivery.ProductDeliveryDTO;
import com.example.demo.service.ProductDeliveryService;
import com.example.demo.service.ProductDeliveryServiceImplementation;
import com.google.gson.Gson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery")
public class ProductDeliveryController {

    private final ProductDeliveryService productDeliveryServiceImplementation;
    private static final Gson gson = new Gson();
    public ProductDeliveryController(ProductDeliveryService productDeliveryServiceImplementation) {
        this.productDeliveryServiceImplementation = productDeliveryServiceImplementation;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDelivery> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.productDeliveryServiceImplementation.getOne(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDelivery>> findAllDelivery() {
        return ResponseEntity.ok(this.productDeliveryServiceImplementation.findAllProductDeliveries());
    }

    @PostMapping("/save")
    public ResponseEntity<ProductDelivery> createDelivery(@RequestBody ProductDeliveryDTO productDelivery){
        return ResponseEntity.ok(this.productDeliveryServiceImplementation.saveProductDelivery(productDelivery));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDelivery> updateDelivery(@RequestBody ProductDelivery productDelivery){
        return ResponseEntity.ok(this.productDeliveryServiceImplementation.updateProductDelivery(productDelivery));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteDelivery(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.productDeliveryServiceImplementation.deleteProductDelivery(id));
    }

    @GetMapping("/{deliveryId}/assign/{productId}")
    public ResponseEntity<String> assignProductToDelivery(@PathVariable("deliveryId") Long deliveryId, @PathVariable("productId") Long productId){
        this.productDeliveryServiceImplementation.assignProductToDelivery(deliveryId, productId);
        return ResponseEntity.ok(gson.toJson("OK"));
    }

    @GetMapping("/{deliveryId}/remove-from-package/{productId}")
    public ResponseEntity<String> removeProductFromPackage(@PathVariable("deliveryId") Long deliveryId, @PathVariable("productId") Long productId){
        this.productDeliveryServiceImplementation.removeProductFromPackage(deliveryId, productId);
        return ResponseEntity.ok(gson.toJson("OK"));
    }

}
