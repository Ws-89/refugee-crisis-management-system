package com.example.demo.controller;

import com.example.demo.models.products.Product;
import com.example.demo.models.products.ProductDTO;
import com.example.demo.models.products.ProductFactoryImplementation;
import com.example.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductFactoryImplementation productFactoryImplementation;

    public ProductController(ProductService productService, ProductFactoryImplementation productFactoryImplementation) {
        this.productService = productService;
        this.productFactoryImplementation = productFactoryImplementation;
    }

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDTO product) {
        Product productToSave = productFactoryImplementation.getInstance(product);
        return ResponseEntity.ok(this.productService.saveProduct(productToSave));
    }

    @GetMapping("/list/hygiene")
    public ResponseEntity<List<Product>> findAllHygieneProducts(){
        return ResponseEntity.ok(productService.findAllHygieneProducts());
    }

    @GetMapping("/list/food")
    public ResponseEntity<List<Product>> findAllFoodProducts(){
        return ResponseEntity.ok(productService.findAllFoodProducts());
    }

    @GetMapping("/list/medical")
    public ResponseEntity<List<Product>> findAllMedicalProducts(){
        return ResponseEntity.ok(productService.findAllMedicalProducts());
    }
}
