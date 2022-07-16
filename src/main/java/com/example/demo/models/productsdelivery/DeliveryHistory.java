package com.example.demo.models.productsdelivery;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_delivery_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DeliveryHistory {

    @Id
    @SequenceGenerator(
            name = "delivery_history_sequence",
            sequenceName = "delivery_history_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "delivery_history_sequence"
    )
    @Column(name = "delivery_history_id")
    private long deliveryHistoryId;
//    @JsonBackReference
    @ManyToOne(
            cascade = CascadeType.MERGE, fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "transport_movement_id", referencedColumnName = "transport_movement_id"
    )
    private TransportMovement transportMovement;
    @OneToOne(
            mappedBy = "deliveryHistory"
    )
    private ProductDelivery productDelivery;

}
