package com.example.demo.models.productsdelivery;

import com.example.demo.models.vehicles.Vehicle;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_transport_movement")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "graph.TransportMovementHandlingEvents",
        attributeNodes = {
            @NamedAttributeNode(value = "handlingEvents", subgraph = "subgraph.handlingEvents"),
            @NamedAttributeNode(value = "startingAddress"),
            @NamedAttributeNode(value = "vehicle"
            ),
            @NamedAttributeNode(value = "deliverySpecification", subgraph = "subgraph.deliverySpecification")},
        subgraphs = {
            @NamedSubgraph(name = "subgraph.deliverySpecification", attributeNodes = {@NamedAttributeNode(value = "deliveryAddress")}),
            @NamedSubgraph(name = "subgraph.handlingEvents", attributeNodes = {@NamedAttributeNode(value = "transportMovement")})
        })
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
    @Column(name = "transport_movement_id")
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
            referencedColumnName = "delivery_specification_id"
    )
    private DeliverySpecification deliverySpecification;
    @OneToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "starting_address_id",
            referencedColumnName = "delivery_address_id"
    )
    private DeliveryAddress startingAddress;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "vehicle_id",
            referencedColumnName = "vehicle_id"
    )
    private Vehicle vehicle;

    public void addHandlingEvent(HandlingEvent event){
        this.handlingEvents.add(event);
        event.setTransportMovement(this);
    }

}
