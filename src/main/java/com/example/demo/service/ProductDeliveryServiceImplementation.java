package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.*;
import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.models.productsdelivery.ProductDeliveryDTO;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductDeliveryServiceImplementation implements ProductDeliveryService{

    private final ProductDeliveryRepository productDeliveryRepository;
    private final ProductRepository productRepository;

    public ProductDeliveryServiceImplementation(ProductDeliveryRepository productDeliveryRepository, ProductRepository productRepository) {
        this.productDeliveryRepository = productDeliveryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDelivery getOne(Long id) {
        return productDeliveryRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Product delivery with id %s not found", id)));
    }

    @Override
    public ProductDelivery saveProductDelivery(ProductDeliveryDTO source){

        ProductDelivery productDelivery = ProductDelivery.builder()
                .deliveryHistory(source.getDeliveryHistory())
                .deliverySpecification(source.getDeliverySpecification())
                .description(source.getDescription())
                .capacity(source.getCapacity())
                .totalWeight(source.getTotalWeight())
                .build();

        Set<Product> productsSet = source.getProducts().stream()
                .map(p -> {
                    Product product = new Product();
                    product.update(p);
                    product.setProductDelivery(productDelivery);
                    return product;
                }).collect(Collectors.toSet());

        productRepository.saveAll(productsSet);

        productDelivery.setProducts(productsSet);

        return productDeliveryRepository.save(productDelivery);
    }

    @Override
    public ProductDelivery updateProductDelivery(ProductDelivery productDelivery) {
        ProductDelivery productToUpdate = getOne(productDelivery.getDeliveryId());
        productToUpdate.update(productDelivery);
        return productDeliveryRepository.save(productToUpdate);
    }

    @Override
    public Long deleteProductDelivery(Long id) {
        ProductDelivery productToDelete = getOne(id);
        productDeliveryRepository.delete(productToDelete);
        return productToDelete.getDeliveryId();
    }

    @Override
    public List<ProductDelivery> findAllProductDeliveries() {
        return productDeliveryRepository.findAll();
    }
}
