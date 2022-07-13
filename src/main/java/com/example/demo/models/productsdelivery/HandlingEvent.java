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
    @ManyToOne(
            cascade = CascadeType.MERGE
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
    @JsonBackReference
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "deliveryHistoryId")
    private DeliveryHistory deliveryHistory;
    @Enumerated(value = EnumType.STRING)
    private HandlingEventState state;
    private LocalDateTime timeStamp;

}
