package com.example.demo.dto;

import com.example.demo.models.cargo.CargoActivityCategory;
import com.example.demo.models.cargo.DeliveryAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoActivityDTO {

    private Long cargoActivityId;
    private LocalDateTime timeStamp;
    private DeliveryAddress deliveryAddress;
    private CargoActivityCategory cargoActivityCategory;
    private Long transportMovementId;
}
