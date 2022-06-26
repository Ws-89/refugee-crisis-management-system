package com.example.demo.service;

import com.example.demo.models.products.Product;
import com.example.demo.models.productsdelivery.HandlingEvent;
import com.example.demo.models.productsdelivery.ProductDelivery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeliveryEventHandlingService {

    List<ProductDelivery> findByCity();
    List<ProductDelivery> findByPostCode();
    ProductDelivery findById(Long id);
    HandlingEvent addTransportEvent(Long id, HandlingEvent handlingEvent);
    HandlingEvent editTransportEvent(HandlingEvent handlingEvent);
    HandlingEvent removeTransportEvent(HandlingEvent handlingEvent);
}
