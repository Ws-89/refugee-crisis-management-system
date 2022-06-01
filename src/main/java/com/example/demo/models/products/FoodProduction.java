package com.example.demo.models.products;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue(value = "Food")
public class FoodProduction extends Product {

    @Enumerated(value = EnumType.STRING)
    private FoodType foodType;

    public FoodProduction(FoodType foodType) {
        this.foodType = foodType;
    }
}
