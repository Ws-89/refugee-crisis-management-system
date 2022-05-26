package com.example.demo.models.materialResources;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue(value = "Food")
public class Food extends MaterialResource {

    @Enumerated(value = EnumType.STRING)
    private FoodType foodType;

    public Food(FoodType foodType) {
        this.foodType = foodType;
    }
}
