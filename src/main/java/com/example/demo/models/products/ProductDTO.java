package com.example.demo.models.products;

import com.example.demo.models.productsdelivery.ProductDelivery;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public interface ProductDTO {

    Long getProductId();
    String getName();
    String getDescription();
    double getWeight();
    Status getReserved();



}
