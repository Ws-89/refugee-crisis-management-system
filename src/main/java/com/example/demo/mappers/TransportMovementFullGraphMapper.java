package com.example.demo.mappers;

import com.example.demo.dto.TransportMovementFullGraphDTO;
import com.example.demo.models.productsdelivery.TransportMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransportMovementFullGraphMapper {

    TransportMovementFullGraphMapper INSTANCE = Mappers.getMapper(TransportMovementFullGraphMapper.class);

    @Mapping(source = "transportMovementId", target = "transportMovementId")
    @Mapping(source = "startingAddress", target = "startingAddress")
    @Mapping(source = "deliveryAddress", target = "deliveryAddress")
    @Mapping(source = "weightOfTheGoods", target = "weightOfTheGoods")
    @Mapping(source = "vehicle", target = "vehicle")
    @Mapping(source = "wayBills", target = "wayBills")
    @Mapping(source = "transportMovementSpecifications", target = "transportMovementSpecifications")
    TransportMovementFullGraphDTO entityToDTO(TransportMovement transportMovement);
}
