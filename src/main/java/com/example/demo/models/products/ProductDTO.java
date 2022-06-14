package com.example.demo.models.products;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {

    private String name;
    private Type type;
    private FoodType foodType;
    private HygienePurpose hygienePurpose;
    private MedicalPurpose medicalPurpose;
    private LocalDateTime expirationDate;
    private String description;
    private double weight;
    private boolean fragile;
    private State state;

    public ProductDTO() {
    }

    public ProductDTO(String name, Type type, FoodType foodType, HygienePurpose hygienePurpose, State state, MedicalPurpose medicalPurpose) {
        this.name = name;
        this.type = type;
        this.foodType = foodType;
        this.hygienePurpose = hygienePurpose;
        this.medicalPurpose = medicalPurpose;
        this.state = state;
    }
}
