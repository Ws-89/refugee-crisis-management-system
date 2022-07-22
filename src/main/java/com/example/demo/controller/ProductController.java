package com.example.demo.controller;

import com.example.demo.models.HttpResponse;
import com.example.demo.models.products.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<HttpResponse> saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(HttpResponse.builder()
                .timeStamp(now().toString())
                .data(Map.of("created", productService.saveProduct(product)))
                .message("Product created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build());
    }

    @PutMapping("/update")
    public ResponseEntity<HttpResponse> updateProduct(@RequestBody Product product){
        return ResponseEntity.ok().body(HttpResponse.builder()
                .timeStamp(now().toString())
                .data(Map.of("object", productService.updateProduct(product)))
                .message("Product updated")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(HttpResponse.builder()
                .timeStamp(now().toString())
                .data(Map.of("deleted", productService.deleteProduct(id)))
                .message("Product deleted")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @GetMapping("/list")
    public ResponseEntity<HttpResponse> findAllHProducts(@RequestParam Optional<String> name,@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(HttpResponse.builder()
                .timeStamp(now().toString())
                .data(Map.of("page", productService.findByNameContaining(name.orElse(""), page.orElse(0), size.orElse(10))))
                .message("Products retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HttpResponse> findOne(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(HttpResponse.builder()
                .timeStamp(now().toString())
                .data(Map.of("object", productService.findById(id)))
                .message("Product retrieved ")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

}
