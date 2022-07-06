package com.example.demo.service;

import com.example.demo.models.products.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    public Product getOne(Long id);
    public Product saveProduct(Product product);
    public Product updateProduct(Product product);
    public Long deleteProduct(Long id);
    public List<Product> findAllProducts();
//    public List<Product> findAllHygieneProducts();
//    public List<Product> findAllFoodProducts();
//    public List<Product> findAllMedicalProducts();
}
