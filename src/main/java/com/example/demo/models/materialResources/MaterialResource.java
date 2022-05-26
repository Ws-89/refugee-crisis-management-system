package com.example.demo.models.materialResources;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "RESOURCE_TYPE")
@NoArgsConstructor
public abstract class MaterialResource {

    @Id
    @SequenceGenerator(
            name = "material_resource_sequence",
            sequenceName = "material_resource_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "material_resource_sequence"
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

    public MaterialResource(String name, long quantity, LocalDateTime expirationDate, String description, double weight, boolean fragile, State state) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.description = description;
        this.weight = weight;
        this.fragile = fragile;
        this.state = state;
    }
}