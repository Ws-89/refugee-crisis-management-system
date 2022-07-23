package com.example.demo.mappers;

import com.example.demo.dto.TransportMovementDTO;
import com.example.demo.models.cargo.TransportMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransportMovementMapper {

    TransportMovementMapper INSTANCE = Mappers.getMapper(TransportMovementMapper.class);

    @Mapping(source = "transportMovementId", target = "transportMovementId")
    @Mapping(source = "startingAddress", target = "startingAddress")
    @Mapping(source = "deliveryAddress", target ="deliveryAddress")
    @Mapping(source = "vehicle", target = "vehicle")
    @Mapping(source = "weightOfTheGoods", target = "weightOfTheGoods")
    @Mapping(source = "arrivalTime", target = "arrivalTime")
    @Mapping(source = "departureTime", target = "departureTime")
    @Mapping(source = "transportStatus", target = "transportStatus")
    TransportMovementDTO entityToDTO(TransportMovement transportMovement);

}
