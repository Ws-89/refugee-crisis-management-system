package com.example.demo.models.productsdelivery;

import com.example.demo.models.vehicles.Vehicle;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_transport_movement")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
            referencedColumnName = "deliverySpecificationId"
    )
    private DeliverySpecification deliverySpecification;
    @OneToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "starting_address_id",
            referencedColumnName = "deliveryAddressId"
    )
    private DeliveryAddress startingAddress;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "vehicle_id",
            referencedColumnName = "vehicleId"
    )
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "vehicleId")
    private Vehicle vehicle;


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
