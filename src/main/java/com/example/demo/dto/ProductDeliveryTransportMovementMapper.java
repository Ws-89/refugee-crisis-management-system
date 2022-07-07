package com.example.demo.dto;

import com.example.demo.models.productsdelivery.ProductDelivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductDeliveryTransportMovementMapper {

    ProductDeliveryTransportMovementMapper INSTANCE = Mappers.getMapper(ProductDeliveryTransportMovementMapper.class);

    @Mapping(source = "deliveryId", target = "deliveryId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "totalWeight", target = "totalWeight")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "startingAddress", target = "startingAddress")
    @Mapping(source = "deliverySpecification", target = "deliverySpecification")
    ProductDeliveryTransportMovementDTO entityToDTO(ProductDelivery productDelivery);

}
