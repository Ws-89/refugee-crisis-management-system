package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.models.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductDTO findById(Long id);
    ProductDTO saveProduct(Product product);
    ProductDTO updateProduct(Product product);
    Long deleteProduct(Long id);
    Page<ProductDTO> findByNameContaining(String name, int page, int size);
}
