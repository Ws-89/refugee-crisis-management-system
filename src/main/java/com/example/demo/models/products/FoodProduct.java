package com.example.demo.models.products;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue(value = "FOOD")
@Data
@NoArgsConstructor
public class FoodProduct extends Product {

    @Enumerated(value = EnumType.STRING)
    private FoodType foodType;

    public FoodProduct(String name, LocalDateTime expirationDate, String description, double weight, boolean fragile, State state, FoodType foodType){
        super(name, expirationDate, description, weight, fragile, state);
        this.foodType = foodType;
    }

}
