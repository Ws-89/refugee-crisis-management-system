package com.example.demo.service;

import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.repo.MaterialResourceDeliveryRepo;
import org.springframework.stereotype.Service;

@Service
public class ProductDeliveryService extends GenericService<ProductDelivery> {

    public ProductDeliveryService(MaterialResourceDeliveryRepo materialResourceDeliveryRepo) {
        super(materialResourceDeliveryRepo);
    }

    public ProductDelivery nextState(Long id){
        ProductDelivery delivery = this.get(id);
        delivery.nextState();
        System.out.println(delivery.getStateName());
        return this.create(delivery);
    }

    public ProductDelivery prevState(Long id){
        ProductDelivery delivery = this.get(id);
        delivery.prevState();
        return this.create(delivery);
    }
}
