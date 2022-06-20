package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.Product;
import com.example.demo.models.products.ProductDTO;
import com.example.demo.models.products.ProductFactoryImplementation;
import com.example.demo.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService{

    private final ProductFactoryImplementation productFactoryImplementation;
    private final ProductRepository productRepository;

    public ProductServiceImplementation(ProductFactoryImplementation productFactoryImplementation, ProductRepository productRepository) {
        this.productFactoryImplementation = productFactoryImplementation;
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(ProductDTO product) {
        Product productToSave = productFactoryImplementation.getInstance(product);
        return productRepository.save(productToSave);
    }

    @Override
    public Product updateProduct(ProductDTO product) {
        Product productToUpdate = productRepository.findById(product.getProductId())
                .orElseThrow(()-> new NotFoundException(String.format("Product with id %s not found", product.getProductId())));
        productToUpdate.update(product);
        return productRepository.save(productToUpdate);
    }

    @Override
    public Long deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("Product with id %s not found", id)));
        productRepository.delete(product);
        return product.getProductId();
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllHygieneProducts() {
        return productRepository.findAllHygieneProducts();
    }

    @Override
    public List<Product> findAllFoodProducts() {
        return productRepository.findAllFoodProducts();
    }

    @Override
    public List<Product> findAllMedicalProducts() {
        return productRepository.findAllMedicalProducts();
    }
}
