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
@DiscriminatorValue(value = "MEDICAMENT")
public class MedicalProduct extends Product {

    @Enumerated(value = EnumType.STRING)
    private MedicalPurpose medicalPurpose;

    public MedicalProduct(String name,  LocalDateTime expirationDate, String description, double weight, Long ammount, boolean fragile, State state, MedicalPurpose medicalPurpose
    ){
        super(name, expirationDate, description, weight, ammount, fragile, state);
        this.medicalPurpose = medicalPurpose;
    }

    @Override
    public void update(ProductDTO product) {
        if(product.getMedicalPurpose() != null){
            super.setName(product.getName());
            super.setExpirationDate(product.getExpirationDate());
            super.setDescription(product.getDescription());
            super.setWeight(product.getWeight());
            super.setAmount(product.getAmount());
            super.setFragile(product.isFragile());
            super.setState(product.getState());
            this.medicalPurpose = product.getMedicalPurpose();
        }
        else {
            throw new IllegalArgumentException("Not a medical product");
        }
    }
}
