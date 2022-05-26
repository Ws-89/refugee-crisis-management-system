package com.example.demo.models.materialResources;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Medicament")
public class MedicalSupply extends MaterialResource {

    private String purpose;

    public MedicalSupply(String purpose) {
        this.purpose = purpose;
    }
}
