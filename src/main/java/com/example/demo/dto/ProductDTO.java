package com.example.demo.dto;

import com.example.demo.models.products.Category;
import com.example.demo.models.products.State;
import com.example.demo.models.products.Status;
import com.example.demo.models.productsdelivery.ProductDelivery;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductDTO( Long productId,
                          LocalDateTime expirationDate,
                          String name,
                          String description,
                          double weight,
                          Long amount,
                          State state,
                          Category category,
                          Status reserved) { }
