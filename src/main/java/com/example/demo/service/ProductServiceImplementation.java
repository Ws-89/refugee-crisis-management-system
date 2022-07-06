package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.Product;
import com.example.demo.models.products.ProductDTO;
import com.example.demo.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getOne(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Product with id %s not found", id)));
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Long deleteProduct(Long id) {
        Product product = getOne(id);
        productRepository.delete(product);
        return product.getProductId();
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

//    @Override
//    public List<Product> findAllHygieneProducts() {
//        return productRepository.findAllHygieneProducts();
//    }
//
//    @Override
//    public List<Product> findAllFoodProducts() {
//        return productRepository.findAllFoodProducts();
//    }
//
//    @Override
//    public List<Product> findAllMedicalProducts() {
//        return productRepository.findAllMedicalProducts();
//    }
}
