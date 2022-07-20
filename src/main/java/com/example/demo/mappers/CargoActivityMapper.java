package com.example.demo.mappers;

import com.example.demo.dto.CargoActivityDTO;
import com.example.demo.models.productsdelivery.CargoActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CargoActivityMapper {

    CargoActivityMapper INSTANCE = Mappers.getMapper(CargoActivityMapper.class);

    @Mapping(source = "cargoActivityId", target = "cargoActivityId")
    @Mapping(source = "timeStamp", target = "timeStamp")
    @Mapping(source = "deliveryAddress", target = "deliveryAddress")
    @Mapping(source = "cargoActivityCategory", target = "cargoActivityCategory")
    @Mapping(source = "transportMovementId", target = "transportMovementId")
    CargoActivityDTO entityToDTO(CargoActivity cargoActivity);
}
