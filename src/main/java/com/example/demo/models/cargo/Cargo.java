package com.example.demo.models.cargo;

import com.example.demo.models.products.Product;
import com.example.demo.models.products.Status;
import com.fasterxml.jackson.annotation.*;
import lombok.*;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Table(name = "tbl_cargo")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedEntityGraphs({@NamedEntityGraph(name = "graph.WholeCargo",
                    attributeNodes = {
                        @NamedAttributeNode(value = "deliveryHistory", subgraph = "subgraph.deliveryHistory"),
                        @NamedAttributeNode(value = "startingAddress"),
                        @NamedAttributeNode(value = "products"),
                        @NamedAttributeNode(value = "deliverySpecification", subgraph = "subgraph.deliverySpecification")},
                    subgraphs = {
                        @NamedSubgraph(name = "subgraph.deliverySpecification",
                            attributeNodes = @NamedAttributeNode(value = "deliveryAddress", subgraph="subgraph.deliveryAddress")),
                        @NamedSubgraph(name = "subgraph.deliveryHistory",
                            attributeNodes = {
                                @NamedAttributeNode(value = "transportMovements"),
                                @NamedAttributeNode(value = "cargoActivityList")
                            }
                        )
                    }),
@NamedEntityGraph(name = "graph.CargoWithoutProducts",
                    attributeNodes = {
                        @NamedAttributeNode(value = "deliveryHistory"),
                        @NamedAttributeNode(value = "startingAddress"),
                        @NamedAttributeNode(value = "deliverySpecification", subgraph = "subgraph.deliverySpecification")},
                    subgraphs = {
                        @NamedSubgraph(name = "subgraph.deliverySpecification",
                                attributeNodes = @NamedAttributeNode(value = "deliveryAddress", subgraph="subgraph.deliveryAddress"))}),
@NamedEntityGraph(name = "graph.CargoOnlyWithProducts", attributeNodes = {@NamedAttributeNode(value = "products")})}
)
@Entity
public class Cargo implements Serializable {

    @Id
    @SequenceGenerator(
            name = "cargo_sequence",
            sequenceName = "cargo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cargo_sequence"
    )
    @Column(name = "cargo_id")
    private long cargoId;
    private String description;
    @NotNull
    private Double totalWeight;
    @Enumerated(value = EnumType.STRING)
    private Status status;


    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "deliveryHistoryId")
    @NotNull
    @OneToOne(cascade= CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_history_id", referencedColumnName = "delivery_history_id")
    private DeliveryHistory deliveryHistory;

    @NotNull
    @OneToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name = "starting_address_id", referencedColumnName = "address_id")
    private Address startingAddress;

    @OneToOne(cascade= CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_specification_id", referencedColumnName = "delivery_specification_id")
    private DeliverySpecification deliverySpecification;
    @NotNull
    @OneToMany(mappedBy = "cargo", orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product){
        this.products.add(product);
        product.setCargo(this);
        this.totalWeight += product.getWeight();
    }

    public void removeProduct(Product product){
        this.products.remove(product);
        product.setCargo(null);
        this.totalWeight -= product.getWeight();
    }

    public long getCargoId() {
        return cargoId;
    }

    public void setCargoId(long cargoId) {
        this.cargoId = cargoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DeliveryHistory getDeliveryHistory() {
        return deliveryHistory;
    }

    public void setDeliveryHistory(DeliveryHistory deliveryHistory) {
        this.deliveryHistory = deliveryHistory;
    }

    public Address getStartingAddress() {
        return startingAddress;
    }

    public void setStartingAddress(Address startingAddress) {
        this.startingAddress = startingAddress;
    }

    public DeliverySpecification getDeliverySpecification() {
        return deliverySpecification;
    }

    public void setDeliverySpecification(DeliverySpecification deliverySpecification) {
        this.deliverySpecification = deliverySpecification;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

//    @JsonManagedReference
    public Set<Product> getProducts() {
        return products;
    }

}
