package com.example.demo.service;

import com.example.demo.models.products.Product;
import com.example.demo.models.products.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    public Product saveProduct(ProductDTO product);
    public Product updateProduct(ProductDTO product);
    public Long deleteProduct(Long id);
    public List<Product> findAllProducts();
    public List<Product> findAllHygieneProducts();
    public List<Product> findAllFoodProducts();
    public List<Product> findAllMedicalProducts();
}
