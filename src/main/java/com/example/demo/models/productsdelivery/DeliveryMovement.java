package com.example.demo.models.productsdelivery;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "tbl_delivery_movement")
@Builder
public class DeliveryMovement {

    @Id
    @SequenceGenerator(
            name = "delivery_movement_sequence",
            sequenceName = "delivery_movement_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "delivery_movement_sequence"
    )
    private Long id;
    @OneToOne()
    private DeliveryAddress deliveryAddress;
    @OneToOne()
    private StartingPointAddress startingPointAddress;
}
