package com.example.demo.dto;

import com.example.demo.models.products.Product;
import com.example.demo.models.products.Status;

import java.util.Set;

public record ProductDeliveryDTO(Long deliveryId,
                                 DeliverySpecificationDTO deliverySpecification,
                                 String description,
                                 DeliveryAddressDTO startingAddress,
                                 Double getTotalWeight,
                                 Set<Product> products,
                                    Status getStatus){}
