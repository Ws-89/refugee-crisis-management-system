package com.example.demo.mappers;

import com.example.demo.dto.CargoTransportMovementDTO;
import com.example.demo.models.cargo.Cargo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CargoTransportMovementMapper {

    CargoTransportMovementMapper INSTANCE = Mappers.getMapper(CargoTransportMovementMapper.class);

    @Mapping(source = "cargoId", target = "cargoId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "totalWeight", target = "totalWeight")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "startingAddress", target = "startingAddress")
    @Mapping(source = "deliverySpecification", target = "deliverySpecification")
    CargoTransportMovementDTO entityToDTO(Cargo cargo);

}
