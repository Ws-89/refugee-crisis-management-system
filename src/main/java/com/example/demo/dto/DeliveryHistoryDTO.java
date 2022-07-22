package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryHistoryDTO {
    private long deliveryHistoryId;
    private CargoTransportMovementDTO cargo;
    private Boolean isLoaded;
    private long currentTransportMovementId;
    private List<CargoActivityDTO> cargoActivityList;
}
