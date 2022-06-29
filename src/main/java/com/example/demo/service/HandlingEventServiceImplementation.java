package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.productsdelivery.HandlingEvent;
import com.example.demo.models.productsdelivery.HandlingEventDTO;
import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.repo.HandlingEventRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HandlingEventServiceImplementation implements HandlingEventService {

    private final ProductDeliveryRepository productDeliveryRepository;
    private final HandlingEventRepository handlingEventRepository;

    public HandlingEventServiceImplementation(ProductDeliveryRepository productDeliveryRepository, HandlingEventRepository handlingEventRepository) {
        this.productDeliveryRepository = productDeliveryRepository;
        this.handlingEventRepository = handlingEventRepository;
    }

    @Override
    @Transactional
    public HandlingEvent saveHandlingEvent(HandlingEventDTO event, Long id) {
        ProductDelivery productDelivery = productDeliveryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));

        HandlingEvent handlingEvent = new HandlingEvent();
        handlingEvent.update(event);
        handlingEvent.setDeliveryHistory(productDelivery.getDeliveryHistory());

        return handlingEventRepository.save(handlingEvent);
    }

    @Override
    public HandlingEvent updateHandlingEvent(HandlingEventDTO event) {
        return null;
    }

    @Override
    public HandlingEvent removeHandlingEvent(Long id) {
        return null;
    }
}
