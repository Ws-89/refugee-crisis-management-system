package com.example.demo.service;

import com.example.demo.models.products.Product;
import com.example.demo.models.products.Type;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    public Product saveProduct(Product product);
    public List<Product> findAllHygieneProducts();
    public List<Product> findAllFoodProducts();
    public List<Product> findAllMedicalProducts();
}
