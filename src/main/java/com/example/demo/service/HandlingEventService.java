package com.example.demo.service;

import com.example.demo.models.productsdelivery.HandlingEvent;
import com.example.demo.models.productsdelivery.HandlingEventDTO;

import java.util.List;

public interface HandlingEventService {

    public List<HandlingEvent> findAllByTransportMovementId(Long id);
    public HandlingEvent getHandlingEvent(Long id);
    public HandlingEvent saveHandlingEvent(HandlingEventDTO event, Long deliveryId, Long transportId);
    public HandlingEvent updateHandlingEvent(HandlingEventDTO event);
    public Long removeHandlingEvent(Long id);

}
