package com.example.demo.models.products;

import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PRODUCT_TYPE")
@NoArgsConstructor
public abstract class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private long id;
    private String name;
    private long quantity;
    private LocalDateTime expirationDate;
    private String description;
    private double weight;
    private boolean fragile;
    @Enumerated(value = EnumType.STRING)
    private State state;

    public Product(String name, long quantity, LocalDateTime expirationDate, String description, double weight, boolean fragile, State state) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.description = description;
        this.weight = weight;
        this.fragile = fragile;
        this.state = state;
    }
}