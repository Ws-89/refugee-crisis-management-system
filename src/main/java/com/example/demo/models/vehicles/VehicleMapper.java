package com.example.demo.models.vehicles;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    @Mapping(source ="vehicleId", target ="vehicleId")
    @Mapping(source ="brand", target ="brand")
    @Mapping(source ="model", target ="model")
    @Mapping(source ="engine", target ="engine")
    @Mapping(source ="capacity", target ="capacity")
    @Mapping(source ="vehicleCategory", target ="vehicleCategory")
    @Mapping(source ="licensePlate", target ="licensePlate")
    VehicleDTO entityToDTO(Vehicle vehicle);
    
}
