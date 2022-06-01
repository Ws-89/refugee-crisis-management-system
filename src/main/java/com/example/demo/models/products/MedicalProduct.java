package com.example.demo.models.products;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Medicament")
public class MedicalProduct extends Product {

    private String purpose;

    public MedicalProduct(String purpose) {
        this.purpose = purpose;
    }
}
