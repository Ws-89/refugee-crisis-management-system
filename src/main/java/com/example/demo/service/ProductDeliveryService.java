package com.example.demo.service;

import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.repo.ProductDeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductDeliveryService extends GenericService<ProductDelivery> {

    public ProductDeliveryService(ProductDeliveryRepository productDeliveryRepository) {
        super(productDeliveryRepository);
    }


}
