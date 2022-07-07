package com.example.demo.models.productsdelivery;

import com.example.demo.models.vehicles.Vehicle;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_transport_movement")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "graph.TransportMovementHandlingEvents",
        attributeNodes = {
            @NamedAttributeNode(value = "handlingEvents"),
            @NamedAttributeNode(value = "startingAddress"),
            @NamedAttributeNode(value = "vehicle"),
            @NamedAttributeNode(value = "deliverySpecification", subgraph = "subgraph.deliverySpecification")},
        subgraphs = {
            @NamedSubgraph(name = "subgraph.deliverySpecification", attributeNodes = {@NamedAttributeNode(value = "deliveryAddress")})
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
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "handlingEventId")
    private List<HandlingEvent> handlingEvents;
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "delivery_specification_id",
            referencedColumnName = "delivery_specification_id"
    )
    private DeliverySpecification deliverySpecification;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "starting_address_id",
            referencedColumnName = "delivery_address_id"
    )
    private DeliveryAddress startingAddress;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "vehicle_id",
            referencedColumnName = "vehicle_id"
    )
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "vehicleId")
    private Vehicle vehicle;

    public void addHandlingEvent(HandlingEvent event){
        this.handlingEvents.add(event);
        event.setTransportMovement(this);
    }

    public Long getTransportMovementId() {
        return transportMovementId;
    }

    public void setTransportMovementId(Long transportMovementId) {
        this.transportMovementId = transportMovementId;
    }

    public List<HandlingEvent> getHandlingEvents() {
        return handlingEvents;
    }

    public void setHandlingEvents(List<HandlingEvent> handlingEvents) {
        this.handlingEvents = handlingEvents;
    }

    public DeliverySpecification getDeliverySpecification() {
        return deliverySpecification;
    }

    public void setDeliverySpecification(DeliverySpecification deliverySpecification) {
        this.deliverySpecification = deliverySpecification;
    }

    public DeliveryAddress getStartingAddress() {
        return startingAddress;
    }

    public void setStartingAddress(DeliveryAddress startingAddress) {
        this.startingAddress = startingAddress;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
