package com.example.demo.controller;

import com.example.demo.models.products.Product;
import com.example.demo.models.products.ProductDTO;
import com.example.demo.models.products.ProductFactoryImplementation;
import com.example.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService, ProductFactoryImplementation productFactoryImplementation) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDTO product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO product){
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> findAllHProducts(){
        return ResponseEntity.ok(productService.findAllProducts());
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
