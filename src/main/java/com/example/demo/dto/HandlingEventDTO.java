package com.example.demo.dto;

import com.example.demo.models.productsdelivery.HandlingEventState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandlingEventDTO {

    private long handlingEventId;
    private DeliveryHistoryDTO deliveryHistory;
    private HandlingEventState state;
    private LocalDateTime timeStamp;
}
