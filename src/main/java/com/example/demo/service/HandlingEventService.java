package com.example.demo.service;

import com.example.demo.models.productsdelivery.HandlingEvent;
import com.example.demo.models.productsdelivery.HandlingEventDTO;

import java.util.List;

public interface HandlingEventService {

    public List<HandlingEvent> handlingEventsOfTransportMovement(Long id);
    public List<HandlingEvent> handlingEventsOfProductDelivery(Long id);
    public HandlingEvent saveHandlingEvent(HandlingEventDTO event, Long deliveryId, Long transportId);
    public HandlingEvent updateHandlingEvent(HandlingEventDTO event);
    public HandlingEvent removeHandlingEvent(Long id);

}
