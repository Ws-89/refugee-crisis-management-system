package com.example.demo.mappers;

import com.example.demo.dto.DeliveryAddressDTO;
import com.example.demo.models.productsdelivery.DeliveryAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

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
