package com.example.demo.service;

import com.example.demo.dto.HandlingEventDTO;
import com.example.demo.models.productsdelivery.HandlingEvent;

import java.util.List;

public interface HandlingEventService {

    public List<HandlingEventDTO> findAllByTransportMovementId(Long id);
    public HandlingEventDTO getHandlingEvent(Long id);
    public HandlingEventDTO saveHandlingEvent(HandlingEvent event, Long deliveryId, Long transportId);
    public HandlingEventDTO updateHandlingEvent(HandlingEvent event);
    public Long removeHandlingEvent(Long id);

}
