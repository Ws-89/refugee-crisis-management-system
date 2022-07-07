package com.example.demo.dto;

import com.example.demo.models.productsdelivery.DeliveryHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryHistoryMapper {

    DeliveryHistoryMapper INSTANCE  = Mappers.getMapper(DeliveryHistoryMapper.class);

    @Mapping(source = "deliveryHistoryId", target = "deliveryHistoryId")
    @Mapping(source = "productDelivery", target = "productDelivery")
    DeliveryHistoryDTO entityToDTO(DeliveryHistory deliveryHistory);
}
