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

    public MedicalProduct(String name,  LocalDateTime expirationDate, String description, double weight, boolean fragile, State state, MedicalPurpose medicalPurpose
    ){
        super(name, expirationDate, description, weight, fragile, state);
        this.medicalPurpose = medicalPurpose;
    }

}
