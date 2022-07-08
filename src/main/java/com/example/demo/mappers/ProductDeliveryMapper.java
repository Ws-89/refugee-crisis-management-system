package com.example.demo.mappers;

import com.example.demo.dto.ProductDeliveryDTO;
import com.example.demo.models.productsdelivery.ProductDelivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductDeliveryMapper {

    ProductDeliveryMapper INSTANCE = Mappers.getMapper(ProductDeliveryMapper.class);

    @Mapping(source = "deliveryId", target ="deliveryId")
    @Mapping(source = "description", target ="description")
    @Mapping(source = "totalWeight", target ="totalWeight")
    @Mapping(source = "status", target ="status")
    @Mapping(source = "startingAddress", target ="startingAddress")
    @Mapping(source = "deliverySpecification", target ="deliverySpecification")
    @Mapping(source = "products", target ="products")
    ProductDeliveryDTO entityToDTO(ProductDelivery productDelivery);
}
