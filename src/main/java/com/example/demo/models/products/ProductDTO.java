package com.example.demo.models.products;

import com.example.demo.models.productsdelivery.ProductDelivery;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long productId;
    private String name;
    private String productType;
    private LocalDateTime expirationDate;
    private String description;
    private double weight;
    private Long amount;
    private Status reserved;
    private boolean fragile;
    private State state;

}
