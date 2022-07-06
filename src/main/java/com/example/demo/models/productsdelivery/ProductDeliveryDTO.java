package com.example.demo.models.productsdelivery;

import com.example.demo.models.products.ProductDTO;
import com.example.demo.models.products.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

public interface ProductDeliveryDTO {

     Long getDeliveryId();
     DeliverySpecification getDeliverySpecification();
     String getDescription();
     DeliveryAddress getStartingAddress();
     Double getTotalWeight();
     Status getStatus();
}
