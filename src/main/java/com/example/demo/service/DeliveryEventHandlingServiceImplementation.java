package com.example.demo.service;

import com.example.demo.models.productsdelivery.HandlingEvent;
import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.repo.HandlingEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryEventHandlingServiceImplementation implements DeliveryEventHandlingService {

    private final ProductDeliveryService productDeliveryService;
    private final HandlingEventRepository handlingEventRepository;

    public DeliveryEventHandlingServiceImplementation(ProductDeliveryService productDeliveryService, HandlingEventRepository handlingEventRepository) {
        this.productDeliveryService = productDeliveryService;
        this.handlingEventRepository = handlingEventRepository;
    }

    @Override
    public List<ProductDelivery> findByCity() {
        return null;
    }

    @Override
    public List<ProductDelivery> findByPostCode() {
        return null;
    }

    @Override
    public ProductDelivery findById(Long id) {
        return null;
    }

    @Override
    public HandlingEvent addTransportEvent(Long id, HandlingEvent handlingEvent) {
        ProductDelivery productDelivery = productDeliveryService.getOne(id);
        productDelivery.getDeliveryHistory().addEvent(handlingEvent);
        return null;
    }

    @Override
    public HandlingEvent editTransportEvent(HandlingEvent handlingEvent) {
        return null;
    }

    @Override
    public HandlingEvent removeTransportEvent(HandlingEvent handlingEvent) {
        return null;
    }
}
