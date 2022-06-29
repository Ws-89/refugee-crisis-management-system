package com.example.demo.models.productsdelivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class HandlingEventDTO {
    private Long handlingEventId;
    private TransportMovement transportMovement;
    private DeliveryHistory deliveryHistory;
    private HandlingEventState state;
    private LocalDateTime timeStamp;
}
