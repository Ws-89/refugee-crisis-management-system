package com.example.demo.models.products;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Hygiene")
public class HygieneProduct extends Product {


}
