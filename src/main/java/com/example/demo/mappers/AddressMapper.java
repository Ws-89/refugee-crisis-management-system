package com.example.demo.mappers;

import com.example.demo.dto.AddressDTO;
import com.example.demo.models.cargo.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "addressId", target = "addressId")
    @Mapping(source = "postCode", target = "postCode")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "state", target = "state")
    AddressDTO entityToDTO(Address address);
}
