package com.example.demo.dto;

import com.example.demo.models.productsdelivery.TransportMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransportMovementMapper {

    TransportMovementMapper INSTANCE = Mappers.getMapper(TransportMovementMapper.class);

    @Mapping(source = "transportMovementId", target = "transportMovementId")
    @Mapping(source = "deliverySpecification", target = "deliverySpecification")
    @Mapping(source = "startingAddress", target = "startingAddress")
    @Mapping(source = "vehicle", target = "vehicle")
    @Mapping(source = "handlingEvents", target = "handlingEvents")
    TransportMovementDTO entityToDTO(TransportMovement transportMovement);

}
