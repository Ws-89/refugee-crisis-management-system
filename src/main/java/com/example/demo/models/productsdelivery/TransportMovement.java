package com.example.demo.models.productsdelivery;

import com.example.demo.models.vehicles.Vehicle;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_transport_movement")
@Builder
@Data
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
//    @JsonManagedReference
    @OneToMany(
            mappedBy = "transportMovement", cascade = CascadeType.MERGE
    )
    private List<DeliveryHistory> wayBills;
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
    private Double weightOfTheGoods;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "vehicle_id",
            referencedColumnName = "vehicle_id"
    )
    private Vehicle vehicle;

    public boolean addProductDelivery(DeliveryHistory packageToBeDelivered){
        if(wayBills.stream().anyMatch(x -> x.getDeliveryHistoryId() == packageToBeDelivered.getDeliveryHistoryId())){
            throw new IllegalStateException("This package is already planned for this shipment");
        }

        Double capacity = vehicle.getCapacity();
        if(weightOfTheGoods + packageToBeDelivered.getProductDelivery().getTotalWeight() > capacity)
            throw new IllegalStateException("This package is too heavy for this shipment");

        this.wayBills.add(packageToBeDelivered);
        packageToBeDelivered.setTransportMovement(this);
        this.weightOfTheGoods += packageToBeDelivered.getProductDelivery().getTotalWeight();
        return true;
    }

}
