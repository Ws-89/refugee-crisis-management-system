package com.example.demo.mappers;

import com.example.demo.dto.DeliveryHistoryDTO;
import com.example.demo.models.productsdelivery.DeliveryHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryHistoryMapper {

    DeliveryHistoryMapper INSTANCE  = Mappers.getMapper(DeliveryHistoryMapper.class);

    @Mapping(source = "deliveryHistoryId", target = "deliveryHistoryId")
    @Mapping(source = "productDelivery", target = "productDelivery")
    @Mapping(source = "cargoActivityList", target = "cargoActivityList")
    @Mapping(source = "isLoaded", target = "isLoaded")
    @Mapping(source = "currentTransportMovementId", target = "currentTransportMovementId")
    DeliveryHistoryDTO entityToDTO(DeliveryHistory deliveryHistory);
}
