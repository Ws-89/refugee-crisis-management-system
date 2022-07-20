package com.example.demo.dto;

import com.example.demo.models.productsdelivery.CargoActivity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryHistoryDTO {
    private long deliveryHistoryId;
    private ProductDeliveryTransportMovementDTO productDelivery;
    private Boolean isLoaded;
    private long currentTransportMovementId;
    private List<CargoActivityDTO> cargoActivityList;
}
