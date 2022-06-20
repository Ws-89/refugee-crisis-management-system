package com.example.demo.models.productsdelivery;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "tbl_transport_movement")
@Builder
public class TransportMovement {

    @Id
    @SequenceGenerator(
            name = "delivery_transport_sequence",
            sequenceName = "delivery_transport_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "delivery_transport_sequence"
    )
    private Long transportMovementId;
    @OneToOne()
    private DeliveryAddress deliveryAddress;
    @OneToOne()
    private StartingPointAddress startingPointAddress;
}
