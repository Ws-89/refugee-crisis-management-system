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
    @JsonIgnore
    @OneToMany(
            mappedBy = "deliveryHistory",
            orphanRemoval = true
    )
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "handlingEventId")
    private List<HandlingEvent> handlingEvents = new ArrayList<HandlingEvent>();
//    @JsonIgnore
    @OneToOne(
            mappedBy = "deliveryHistory"
    )
    private ProductDelivery productDelivery;

    public void addEvent(HandlingEvent event){
        this.handlingEvents.add(event);
        event.setDeliveryHistory(this);
    }

    public List<HandlingEvent> getHandlingEvents() {
        return handlingEvents;
    }

    public void setHandlingEvents(List<HandlingEvent> handlingEvents) {
        this.handlingEvents = handlingEvents;
    }

    public ProductDelivery getProductDelivery() {
        return productDelivery;
    }

    public void setProductDelivery(ProductDelivery productDelivery) {
        this.productDelivery = productDelivery;
    }

    public long getDeliveryHistoryId() {
        return deliveryHistoryId;
    }
}
