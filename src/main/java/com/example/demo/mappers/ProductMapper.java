package com.example.demo.mappers;

import com.example.demo.dto.ProductDTO;
import com.example.demo.models.products.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "productId", target ="productId")
    @Mapping(source = "name", target ="name")
    @Mapping(source = "expirationDate", target ="expirationDate")
    @Mapping(source = "description", target ="description")
    @Mapping(source = "weight", target ="weight")
    @Mapping(source = "amount", target ="amount")
    @Mapping(source = "reserved", target ="reserved")
    @Mapping(source = "fragile", target ="fragile")
    @Mapping(source = "state", target ="state")
    @Mapping(source = "category", target ="category")
    ProductDTO productToProductDTO(Product product);












}
