package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.*;
import com.example.demo.models.productsdelivery.DeliveryAddress;
import com.example.demo.models.productsdelivery.DeliverySpecification;
import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.models.productsdelivery.ProductDeliveryDTO;
import com.example.demo.repo.DeliveryAddressRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductDeliveryServiceImplementation implements ProductDeliveryService{

    private final ProductDeliveryRepository productDeliveryRepository;
    private final ProductRepository productRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

    public ProductDeliveryServiceImplementation(ProductDeliveryRepository productDeliveryRepository,
                                                ProductRepository productRepository,
                                                DeliveryAddressRepository deliveryAddressRepository) {
        this.productDeliveryRepository = productDeliveryRepository;
        this.productRepository = productRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    @Override
    public ProductDelivery getOne(Long id) {
        return productDeliveryRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Product delivery with id %s not found", id)));
    }

    @Override
    @Transactional
    public ProductDelivery saveProductDelivery(ProductDeliveryDTO source){

        DeliveryAddress deliveryAddress = deliveryAddressRepository.save(source.getDeliverySpecification().getDeliveryAddress());
        DeliverySpecification deliverySpecification = source.getDeliverySpecification();
        deliverySpecification.setDeliveryAddress(deliveryAddress);

        ProductDelivery productDelivery = ProductDelivery.builder()
                .deliveryHistory(source.getDeliveryHistory())
                .deliverySpecification(deliverySpecification)
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

        productDelivery.setProducts(productRepository.saveAll(productsSet).stream().collect(Collectors.toSet()));

        return productDelivery.getProducts().stream().findFirst().get().getProductDelivery();
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
