package com.example.demo.models.productsdelivery;

import com.example.demo.models.vehicles.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_transport_movement")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @OneToMany(
            mappedBy = "transportMovement"
    )
    private List<HandlingEvent> handlingEvents;
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "delivery_specification_id",
            referencedColumnName = "deliverySpecificationId"
    )
    private DeliverySpecification deliverySpecification;
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "starting_address_id",
            referencedColumnName = "deliveryAddressId"
    )
    private DeliveryAddress startingAddress;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "vehicle_id",
            referencedColumnName = "vehicleId"
    )
    private Vehicle vehicle;
}
