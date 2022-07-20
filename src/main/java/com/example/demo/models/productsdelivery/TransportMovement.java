package com.example.demo.models.productsdelivery;

import com.example.demo.models.vehicles.Vehicle;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbl_transport_movement")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "graph.TransportMovement",
        attributeNodes = {
            @NamedAttributeNode(value = "startingAddress"),
            @NamedAttributeNode(value = "vehicle"),
            @NamedAttributeNode(value = "deliveryAddress")
        })
@NamedEntityGraph(name = "graph.TransportMovementDeliverySpecification",
        attributeNodes = {
            @NamedAttributeNode(value = "startingAddress"),
            @NamedAttributeNode(value = "vehicle"),
            @NamedAttributeNode(value = "deliveryAddress"),
            @NamedAttributeNode(value = "transportMovementSpecifications", subgraph = "subgraph.transportMovementSpecifications")
        }, subgraphs = {
            @NamedSubgraph(name = "subgraph.transportMovementSpecifications", attributeNodes = {
                @NamedAttributeNode(value = "deliveryAddress")
        })
})
@NamedEntityGraph(name = "graph.TransportMovementWithPackages",
        attributeNodes = {
            @NamedAttributeNode(value = "wayBills", subgraph = "subgraph.wayBills")

        }, subgraphs = {
        @NamedSubgraph(name = "subgraph.wayBills", attributeNodes = {
                @NamedAttributeNode(value = "productDelivery", subgraph = "subgraph.productDelivery")
        }),
        @NamedSubgraph(name = "subgraph.productDelivery", attributeNodes = {
                @NamedAttributeNode(value = "deliveryHistory"),
                @NamedAttributeNode(value = "startingAddress"),
                @NamedAttributeNode(value = "deliverySpecification", subgraph = "subgraph.deliverySpecification"),
        }),
        @NamedSubgraph(name = "subgraph.deliverySpecification",
                attributeNodes = @NamedAttributeNode(value = "deliveryAddress", subgraph="subgraph.deliveryAddress"))

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
    @ManyToMany(
            cascade = CascadeType.MERGE
    )
    @JoinTable(
            name = "transport_package_map",
            joinColumns = @JoinColumn(
                    name = "transport_movement_id", referencedColumnName = "transport_movement_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "delivery_history_id", referencedColumnName = "delivery_history_id"
            )
    )
    private Set<DeliveryHistory> wayBills;
    @OrderColumn
    @OneToMany(
            mappedBy = "transportMovement", cascade = CascadeType.ALL, orphanRemoval = true
    )
    private List<TransportMovementSpecification> transportMovementSpecifications;
    @OneToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "starting_address_id",
            referencedColumnName = "delivery_address_id"
    )
    private DeliveryAddress startingAddress;
    @OneToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "delivery_address_id",
            referencedColumnName = "delivery_address_id"
    )
    private DeliveryAddress deliveryAddress;
    @Enumerated(value = EnumType.STRING)
    private TransportStatus transportStatus;
    private Double weightOfTheGoods;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "vehicle_id",
            referencedColumnName = "vehicle_id"
    )
    private Vehicle vehicle;

    public boolean removePackage(DeliveryHistory packageToBeRemoved){
        if(wayBills.remove(packageToBeRemoved)){
            this.weightOfTheGoods -= packageToBeRemoved.getProductDelivery().getTotalWeight();
            return true;
        }
        return false;
    }

    public boolean addProductDelivery(DeliveryHistory packageToBeDelivered){
        if(this.wayBills == null){
            this.wayBills = new HashSet<>();
        }
        if(wayBills.stream().anyMatch(x -> x.getDeliveryHistoryId() == packageToBeDelivered.getDeliveryHistoryId())){
            throw new IllegalStateException("This package is already planned for this shipment");
        }

        Double capacity = vehicle.getCapacity();
        if(weightOfTheGoods + packageToBeDelivered.getProductDelivery().getTotalWeight() > capacity)
            throw new IllegalStateException("This package is too heavy for this shipment");


        wayBills.add(packageToBeDelivered);
        this.weightOfTheGoods += packageToBeDelivered.getProductDelivery().getTotalWeight();
        return true;
    }

    public void generateARoute(){
//        this.transportMovementSpecifications = this.wayBills.stream().map(w -> {
//            TransportMovementSpecification tms = new TransportMovementSpecification();
//            tms.setArrivalTime(w.getProductDelivery().getDeliverySpecification().getArrivalTime());
//            tms.setDeliveryAddress(w.getProductDelivery().getDeliverySpecification().getDeliveryAddress());
//            return tms;
//        }).distinct().collect(Collectors.toList());

    };

    public boolean checkIfStopsAtAddress(DeliveryAddress deliveryAddress){
        if(this.deliveryAddress.equals(deliveryAddress) || this.startingAddress.equals(deliveryAddress)) {
            return true;
        }else if (this.wayBills.stream().anyMatch(wayBill -> wayBill.getProductDelivery().getStartingAddress() == deliveryAddress
                || wayBill.getProductDelivery().getDeliverySpecification().getDeliveryAddress() == deliveryAddress)){
            return true;
        }
        else return false;
    }


    public Long getTransportMovementId() {
        return transportMovementId;
    }

    public void setTransportMovementId(Long transportMovementId) {
        this.transportMovementId = transportMovementId;
    }

    public Set<DeliveryHistory> getWayBills() {
        return wayBills;
    }

    public void setWayBills(Set<DeliveryHistory> wayBills) {
        this.wayBills = wayBills;
    }

    public List<TransportMovementSpecification> getTransportMovementSpecifications() {
        return transportMovementSpecifications;
    }

    public void setTransportMovementSpecifications(List<TransportMovementSpecification> transportMovementSpecifications) {
        this.transportMovementSpecifications = transportMovementSpecifications;
    }

    public DeliveryAddress getStartingAddress() {
        return startingAddress;
    }

    public void setStartingAddress(DeliveryAddress startingAddress) {
        this.startingAddress = startingAddress;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Double getWeightOfTheGoods() {
        return weightOfTheGoods;
    }

    public void setWeightOfTheGoods(Double weightOfTheGoods) {
        this.weightOfTheGoods = weightOfTheGoods;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public TransportStatus getTransportStatus() {
        return transportStatus;
    }

    public void setTransportStatus(TransportStatus transportStatus) {
        this.transportStatus = transportStatus;
    }
}
