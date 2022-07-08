package com.example.demo.mappers;

import com.example.demo.dto.HandlingEventDTO;
import com.example.demo.models.productsdelivery.HandlingEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HandlingEventMapper {

    HandlingEventMapper INSTANCE = Mappers.getMapper(HandlingEventMapper.class);

    @Mapping(source = "handlingEventId", target = "handlingEventId")
    @Mapping(source = "deliveryHistory", target = "deliveryHistory")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "timeStamp", target = "timeStamp")
    HandlingEventDTO entityToDTO(HandlingEvent handlingEvent);
}
