package com.example.demo.models.productsdelivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public interface HandlingEventDTO {
    Long getHandlingEventId();
    DeliveryHistoryDTO getDeliveryHistory();
    HandlingEventState getState();
    LocalDateTime getTimeStamp();
}
