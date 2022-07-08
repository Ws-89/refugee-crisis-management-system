package com.example.demo.mappers;

import com.example.demo.dto.DeliverySpecificationDTO;
import com.example.demo.models.productsdelivery.DeliverySpecification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliverySpecificationMapper {

    DeliverySpecificationMapper INSTANCE = Mappers.getMapper(DeliverySpecificationMapper.class);

    @Mapping(source = "deliverySpecificationId", target ="deliverySpecificationId")
    @Mapping(source = "arrivalTime", target ="arrivalTime")
    @Mapping(source = "deliveryAddress", target ="deliveryAddress")
    DeliverySpecificationDTO entityToDTO(DeliverySpecification deliverySpecification);
}
