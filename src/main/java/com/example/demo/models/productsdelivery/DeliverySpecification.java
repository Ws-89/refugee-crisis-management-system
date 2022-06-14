package com.example.demo.models.productsdelivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_delivery_specification")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
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
    private Long deliverySpecificationId;
    private LocalDateTime expectedDeliveryDateTime;
    @OneToOne(
            cascade=CascadeType.ALL
    )
    private DeliveryAddress deliveryAddress;
}
