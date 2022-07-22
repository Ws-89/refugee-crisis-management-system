package com.example.demo.mappers;

import com.example.demo.dto.CargoDTO;
import com.example.demo.models.cargo.Cargo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CargoMapper {

    CargoMapper INSTANCE = Mappers.getMapper(CargoMapper.class);

    @Mapping(source = "cargoId", target ="cargoId")
    @Mapping(source = "description", target ="description")
    @Mapping(source = "totalWeight", target ="totalWeight")
    @Mapping(source = "status", target ="status")
    @Mapping(source = "startingAddress", target ="startingAddress")
    @Mapping(source = "deliverySpecification", target ="deliverySpecification")
    @Mapping(source = "products", target ="products")
    CargoDTO entityToDTO(Cargo cargo);
}
