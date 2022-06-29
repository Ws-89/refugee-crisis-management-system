package com.example.demo.service;

import com.example.demo.models.productsdelivery.HandlingEvent;
import com.example.demo.models.productsdelivery.HandlingEventDTO;

public interface HandlingEventService {

    public HandlingEvent saveHandlingEvent(HandlingEventDTO event, Long id);
    public HandlingEvent updateHandlingEvent(HandlingEventDTO event);
    public HandlingEvent removeHandlingEvent(Long id);

}
