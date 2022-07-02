package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.*;
import com.example.demo.models.productsdelivery.*;
import com.example.demo.repo.DeliveryAddressRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductDeliveryServiceImplementation implements ProductDeliveryService{

    private final ProductDeliveryRepository productDeliveryRepository;
    private final ProductRepository productRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final ProductDeliveryDAO productDeliveryDao;


    public ProductDeliveryServiceImplementation(ProductDeliveryRepository productDeliveryRepository,
                                                ProductRepository productRepository,
                                                DeliveryAddressRepository deliveryAddressRepository, ProductDeliveryDAO productDeliveryDao) {
        this.productDeliveryRepository = productDeliveryRepository;
        this.productRepository = productRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.productDeliveryDao = productDeliveryDao;
    }

    @Override
    public ProductDelivery getOne(Long id) {
        return productDeliveryRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Product delivery with id %s not found", id)));
    }

    @Override
    @Transactional
    public ProductDelivery saveProductDelivery(ProductDeliveryDTO source){

        DeliveryAddress destinationAddress = deliveryAddressRepository.findById(source.getDeliverySpecification()
                .getDeliveryAddress().getDeliveryAddressId()).orElseThrow(()-> new NotFoundException("Addres not found"));

        DeliveryAddress startingAddress = deliveryAddressRepository.findById(source.getStartingAddress().getDeliveryAddressId())
                .orElseThrow(()-> new NotFoundException("Addres not found"));

        DeliverySpecification deliverySpecification = source.getDeliverySpecification();
        deliverySpecification.setDeliveryAddress(destinationAddress);

        ProductDelivery productDelivery = ProductDelivery.builder()
                .deliveryHistory(source.getDeliveryHistory())
                .deliverySpecification(deliverySpecification)
                .startingAddress(startingAddress)
                .description(source.getDescription())
                .totalWeight(source.getTotalWeight())
                .status(source.getStatus())
                .build();

        Set<Product> productsSet = source.getProducts().stream()
                .map(p ->
                        Product.builder()
                                .productId(p.getProductId())
                                .name(p.getName())
                                .expirationDate(p.getExpirationDate())
                                .description(p.getDescription())
                                .weight(p.getWeight())
                                .amount(p.getAmount())
                                .reserved(Status.Reserved)
                                .fragile(p.isFragile())
                                .state(p.getState())
                                .category(p.getCategory())
                                .productDelivery(productDelivery)
                                .build()
                ).collect(Collectors.toSet());

        productDelivery.setProducts(productsSet);

        return productRepository.saveAll(productsSet).stream().collect(Collectors.toSet()).stream().findFirst().get().getProductDelivery();
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
