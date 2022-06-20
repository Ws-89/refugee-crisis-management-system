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

    public FoodProduct(String name,
                       LocalDateTime expirationDate,
                       String description,
                       double weight,
                       Long ammount,
                       boolean fragile,
                       State state,
                       FoodType foodType){
        super(name, expirationDate, description, weight, ammount, fragile, state);
        this.foodType = foodType;
    }

    @Override
    public void update(ProductDTO product) {
        if(product.getFoodType() != null){
            super.setName(product.getName());
            super.setExpirationDate(product.getExpirationDate());
            super.setDescription(product.getDescription());
            super.setWeight(product.getWeight());
            super.setAmount(product.getAmount());
            super.setFragile(product.isFragile());
            super.setState(product.getState());
            this.foodType = product.getFoodType();
        }
        else {
            throw new IllegalArgumentException("Not a food product");
        }
    }
}
