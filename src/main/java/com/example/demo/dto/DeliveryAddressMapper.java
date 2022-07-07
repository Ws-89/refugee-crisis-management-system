package com.example.demo.dto;

import com.example.demo.models.productsdelivery.DeliveryAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface DeliveryAddressMapper {

    DeliveryAddressMapper INSTANCE = Mappers.getMapper(DeliveryAddressMapper.class);

    @Mapping(source = "deliveryAddressId", target = "deliveryAddressId")
    @Mapping(source = "postCode", target = "postCode")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "state", target = "state")
    DeliveryAddressDTO entityToDTO(DeliveryAddress deliveryAddress);
}
