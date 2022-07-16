package com.example.demo.models.productsdelivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_transport_movement_specification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportMovementSpecification {
    @Id
    @SequenceGenerator(
            name = "transport_movement_specification_sequence",
            sequenceName = "transport_movement_specification_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transport_movement_specification_sequence"
    )
    @Column(name = "transport_movement_specification_id")
    private Long transportMovementSpecificationId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "transport_movement_id", referencedColumnName = "transport_movement_id")
    private TransportMovement transportMovement;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id", referencedColumnName = "delivery_address_id")
    private DeliveryAddress deliveryAddress;
}
