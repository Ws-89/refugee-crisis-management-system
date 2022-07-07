package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.models.products.Product;

import java.util.List;

public interface ProductService {

    ProductDTO findById(Long id);
    ProductDTO saveProduct(Product product);
    ProductDTO updateProduct(Product product);
    Long deleteProduct(Long id);
    List<ProductDTO> findAllProducts();
}
