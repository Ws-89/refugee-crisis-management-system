package com.example.demo.models.productsdelivery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_delivery_specification")
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class DeliverySpecification {

    @Id
    @SequenceGenerator(
            name = "delivery_specification_sequence",
            sequenceName = "delivery_specification_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "delivery_specification_sequence"
    )
    @Column(name = "delivery_specification_id")
    private Long deliverySpecificationId;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id", referencedColumnName = "delivery_address_id")
    private DeliveryAddress deliveryAddress;

}
