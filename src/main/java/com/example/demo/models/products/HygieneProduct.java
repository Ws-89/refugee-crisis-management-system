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

    public HygieneProduct(String name, LocalDateTime expirationDate,  String description, double weight, boolean fragile, State state, HygienePurpose purpose
    ) {
        super(name, expirationDate, description, weight, fragile, state);
        this.hygienePurpose = purpose;
    }

}
