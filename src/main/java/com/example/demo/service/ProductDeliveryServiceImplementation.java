package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.*;
import com.example.demo.models.productsdelivery.*;
import com.example.demo.repo.DeliveryAddressRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;

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
    public ProductDelivery saveProductDelivery(ProductDelivery source){

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

        return productDeliveryRepository.save(productDelivery);
    }

    public void assignProductToDelivery(Long deliveryId, Long productId){
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        ProductDelivery productDelivery = productDeliveryRepository.findById(deliveryId).orElseThrow(() -> new NotFoundException("Product delivery not found"));

        product.setReserved(Status.Reserved);
        productDelivery.addProduct(product);
        productRepository.save(product);
    }

    public void removeProductFromPackage(Long deliveryId, Long productId){
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        ProductDelivery productDelivery = productDeliveryRepository.findById(deliveryId).orElseThrow(() -> new NotFoundException("Product delivery not found"));

        product.setReserved(Status.Available);
        productDelivery.removeProduct(product);
        productRepository.save(product);
    }

    @Override
    public ProductDelivery updateProductDelivery(ProductDelivery productDelivery) {
        ProductDelivery productToUpdate = getOne(productDelivery.getDeliveryId());
        productToUpdate.update(productDelivery);
        return productDeliveryRepository.save(productToUpdate);
    }

    @Override
    public List<ProductDelivery> selectProductDeliveryForDisplayingWithoutProducts() {

        return null;
    }

    @Override
    public Long deleteProductDelivery(Long id) {
        ProductDelivery productToDelete = getOne(id);
        productDeliveryRepository.delete(productToDelete);
        return productToDelete.getDeliveryId();
    }

    @Override
    public List<ProductDelivery> findAllProductDelivery() {
//        return productDeliveryRepository.findAll();
        return productDeliveryRepository.findAll();
    }


}
