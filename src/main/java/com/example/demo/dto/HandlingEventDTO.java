package com.example.demo.dto;

import com.example.demo.dto.DeliveryHistoryDTO;
import com.example.demo.models.productsdelivery.HandlingEventState;
import com.example.demo.models.productsdelivery.TransportMovement;

import java.time.LocalDateTime;

public record HandlingEventDTO(Long handlingEventId,
                               DeliveryHistoryDTO deliveryHistory,
                               TransportMovementDTO transportMovement,
                               HandlingEventState state,
                               LocalDateTime timeStamp) { }
