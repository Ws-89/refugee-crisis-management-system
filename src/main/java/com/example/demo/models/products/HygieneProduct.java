package com.example.demo.models.products;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue(value = "HYGIENE")
public class HygieneProduct extends Product {

    @Enumerated(value = EnumType.STRING)
    private HygienePurpose hygienePurpose;

    public HygieneProduct(String name, LocalDateTime expirationDate,  String description, double weight, Long ammount, boolean fragile, State state, HygienePurpose purpose
    ) {
        super(name, expirationDate, description, weight, ammount, fragile, state);
        this.hygienePurpose = purpose;
    }

    @Override
    public void update(ProductDTO product) {
        if(product.getHygienePurpose() != null){
            super.setName(product.getName());
            super.setExpirationDate(product.getExpirationDate());
            super.setDescription(product.getDescription());
            super.setWeight(product.getWeight());
            super.setAmount(product.getAmount());
            super.setFragile(product.isFragile());
            super.setState(product.getState());
            this.hygienePurpose = product.getHygienePurpose();
        }
        else {
            throw new IllegalArgumentException("Not a hygiene product");
        }
    }
}
