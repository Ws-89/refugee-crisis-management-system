package com.example.demo.models.productsdelivery;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_delivery_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private long deliveryHistoryId;
    @OneToMany(
            mappedBy = "deliveryHistory",
            orphanRemoval = true
    )
    private List<HandlingEvent> handlingEvents = new ArrayList<HandlingEvent>();
    @OneToOne(
            mappedBy = "deliveryHistory"
    )
    private ProductDelivery productDelivery;

    public void addEvent(HandlingEvent event){
        this.handlingEvents.add(event);
    }
}
