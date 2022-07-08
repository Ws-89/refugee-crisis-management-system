package com.example.demo.service;

import com.example.demo.dto.ProductDeliveryDTO;
import com.example.demo.mappers.ProductDeliveryMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.*;
import com.example.demo.models.productsdelivery.*;
import com.example.demo.repo.DeliveryAddressRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public ProductDeliveryDTO findById(Long id) {
        return productDeliveryRepository.findById(id)
                .map(p -> ProductDeliveryMapper.INSTANCE.entityToDTO(p))
                .orElseThrow(() -> new NotFoundException(String.format("Product delivery with id %s not found", id)));
    }

    @Override
    @Transactional
    public ProductDeliveryDTO saveProductDelivery(ProductDelivery source){

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

        return ProductDeliveryMapper.INSTANCE.entityToDTO(productDeliveryRepository.save(productDelivery));
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
    public ProductDeliveryDTO updateProductDelivery(ProductDelivery productDelivery) {
        return productDeliveryRepository.findById(productDelivery.getDeliveryId())
                .map(p -> { p.update(productDelivery);
                    productDeliveryRepository.save(p);
                    return ProductDeliveryMapper.INSTANCE.entityToDTO(p);
                })
                .orElseThrow(() -> new NotFoundException(String.format("Product delivery with id %s not found", productDelivery.getDeliveryId())));
    }

    @Override
    public Long deleteProductDelivery(Long id) {
        return productDeliveryRepository.findById(id)
                .map(p -> {
                    productDeliveryRepository.delete(p);
                    return p.getDeliveryId();
                })
                .orElseThrow(() -> new NotFoundException(String.format("Product delivery with id %s not found", id)));
    }

    @Override
    public List<ProductDeliveryDTO> findAllProductDelivery() {
        return productDeliveryRepository.findAll().stream().map(p -> ProductDeliveryMapper.INSTANCE.entityToDTO(p)).collect(Collectors.toList());
    }


}
