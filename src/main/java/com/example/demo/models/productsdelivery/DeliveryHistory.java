package com.example.demo.models.productsdelivery;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_delivery_history")
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
    @Column(name = "delivery_history_id")
    private long deliveryHistoryId;
    @ManyToMany(
            mappedBy = "wayBills", cascade = CascadeType.MERGE
    )
    Set<TransportMovement> transportMovements;
    @OneToOne(
            mappedBy = "deliveryHistory"
    )
    private ProductDelivery productDelivery;
    private Long finalDestinationId;
    public long getDeliveryHistoryId() {
        return deliveryHistoryId;
    }

    public void setDeliveryHistoryId(long deliveryHistoryId) {
        this.deliveryHistoryId = deliveryHistoryId;
    }

    public Set<TransportMovement> getTransportMovements() {
        return transportMovements;
    }

    public void setTransportMovements(Set<TransportMovement> transportMovements) {
        this.transportMovements = transportMovements;
    }

    public ProductDelivery getProductDelivery() {
        return productDelivery;
    }

    public void setProductDelivery(ProductDelivery productDelivery) {
        this.productDelivery = productDelivery;
    }

    public Long getFinalDestinationId() {
        return finalDestinationId;
    }

    public void setFinalDestinationId(Long finalDestinationId) {
        this.finalDestinationId = finalDestinationId;
    }

    //    public void addTransportMovement(TransportMovement transportMovement){
//        if(this.transportMovements == null){
//            this.transportMovements = new HashSet<>();
//        }
//        this.transportMovements.add(transportMovement);
//    }

}
