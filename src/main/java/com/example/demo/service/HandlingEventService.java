package com.example.demo.service;

import com.example.demo.dto.HandlingEventDTO;
import com.example.demo.models.productsdelivery.HandlingEvent;

import java.util.List;

public interface HandlingEventService {

    public List<HandlingEventDTO> findAllByTransportMovementId(Long id);
    public HandlingEvent getHandlingEvent(Long id);
    public HandlingEvent saveHandlingEvent(HandlingEvent event, Long deliveryId, Long transportId);
    public HandlingEvent updateHandlingEvent(HandlingEvent event);
    public Long removeHandlingEvent(Long id);

}
