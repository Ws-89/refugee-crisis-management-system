package com.example.demo.models.products;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long productId;
    private String name;
    private ProductType productType;
    private FoodType foodType;
    private HygienePurpose hygienePurpose;
    private MedicalPurpose medicalPurpose;
    private LocalDateTime expirationDate;
    private String description;
    private double weight;
    private Long amount;
    private boolean fragile;
    private State state;
}
