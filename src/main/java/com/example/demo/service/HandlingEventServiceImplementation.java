package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.productsdelivery.HandlingEvent;
import com.example.demo.models.productsdelivery.HandlingEventDTO;
import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.models.productsdelivery.TransportMovement;
import com.example.demo.repo.HandlingEventRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.repo.TransportMovementRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HandlingEventServiceImplementation implements HandlingEventService {

    private final ProductDeliveryRepository productDeliveryRepository;
    private final HandlingEventRepository handlingEventRepository;
    private final TransportMovementRepo transportMovementRepo;

    public HandlingEventServiceImplementation(ProductDeliveryRepository productDeliveryRepository, HandlingEventRepository handlingEventRepository, TransportMovementRepo transportMovementRepo) {
        this.productDeliveryRepository = productDeliveryRepository;
        this.handlingEventRepository = handlingEventRepository;
        this.transportMovementRepo = transportMovementRepo;
    }

    @Override
    public List<HandlingEvent> handlingEventsOfTransportMovement(Long id) {
        return null;
    }

    @Override
    public List<HandlingEvent> handlingEventsOfProductDelivery(Long id) {
        return null;
    }

    @Override
    @Transactional
    public HandlingEvent saveHandlingEvent(HandlingEventDTO event, Long deliveryId, Long transportId) {
        ProductDelivery productDelivery = productDeliveryRepository.findById(deliveryId).orElseThrow(() -> new NotFoundException("Delivery not found"));
        TransportMovement transportMovement = transportMovementRepo.findById(transportId).orElseThrow(() -> new NotFoundException("Transport not found"));


        HandlingEvent handlingEvent = new HandlingEvent();

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
