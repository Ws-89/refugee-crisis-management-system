package com.example.demo.mappers;

import com.example.demo.dto.DeliverySpecificationDTO;
import com.example.demo.dto.TransportMovementSpecificationDTO;
import com.example.demo.models.productsdelivery.DeliverySpecification;
import com.example.demo.models.productsdelivery.TransportMovementSpecification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransportMovementSpecificationMapper {

    TransportMovementSpecificationMapper INSTANCE = Mappers.getMapper(TransportMovementSpecificationMapper.class);

    @Mapping(source = "transportMovementSpecificationId", target ="transportMovementSpecificationId")
    @Mapping(source = "arrivalTime", target ="arrivalTime")
    @Mapping(source = "departureTime", target = "departureTime")
    @Mapping(source = "deliveryAddress", target ="deliveryAddress")
    TransportMovementSpecificationDTO entityToDTO(TransportMovementSpecification transportMovementSpecification);
}
