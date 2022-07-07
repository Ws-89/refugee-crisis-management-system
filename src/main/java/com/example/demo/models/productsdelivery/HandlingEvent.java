package com.example.demo.models.productsdelivery;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.example.demo.models.productsdelivery.HandlingEventState.LOADING_EVENT;
import static com.example.demo.models.productsdelivery.HandlingEventState.UNLOADING_EVENT;

@Entity
@Table(name = "tbl_handling_event")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@NamedEntityGraph(name = "graph.handlingEventTransportMovement",
        attributeNodes = {
            @NamedAttributeNode(value = "transportMovement", subgraph = "subgraph.HandlingEventTransportMovement"),
            @NamedAttributeNode(value = "deliveryHistory", subgraph = "subgraph.HandlingEventDeliveryHistory")
        },
        subgraphs = {
            @NamedSubgraph(name = "subgraph.HandlingEventTransportMovement", attributeNodes = {@NamedAttributeNode(value = "deliverySpecification")}),
            @NamedSubgraph(name = "subgraph.HandlingEventDeliveryHistory", attributeNodes = {@NamedAttributeNode(value = "productDelivery")})
        })
public class HandlingEvent {

    @Id
    @SequenceGenerator(
            name = "handling_event_sequence",
            sequenceName = "handling_event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "handling_event_sequence"
    )
    @Column(name = "handling_event_id")
    private long handlingEventId;
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(
            cascade = CascadeType.MERGE
//            ,
//            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "transport_movement_id",
            referencedColumnName = "transport_movement_id"
    )
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "transportMovementId")
    private TransportMovement transportMovement;
    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "delivery_history_id",
            referencedColumnName = "delivery_history_id"
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "deliveryHistoryId")
    private DeliveryHistory deliveryHistory;
    @Enumerated(value = EnumType.STRING)
    private HandlingEventState state;
    private LocalDateTime timeStamp;

    public long getHandlingEventId() {
        return handlingEventId;
    }

    public void setHandlingEventId(long handlingEventId) {
        this.handlingEventId = handlingEventId;
    }

    public TransportMovement getTransportMovement() {
        return transportMovement;
    }

    public DeliveryHistory getDeliveryHistory() {
        return deliveryHistory;
    }

    public void setDeliveryHistory(DeliveryHistory deliveryHistory) {
        this.deliveryHistory = deliveryHistory;
    }

    public HandlingEventState getState() {
        return state;
    }

    public void setState(HandlingEventState state) {
        this.state = state;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setTransportMovement(TransportMovement transportMovement) {
        this.transportMovement = transportMovement;
    }


}
