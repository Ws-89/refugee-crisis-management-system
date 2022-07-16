package com.example.demo.models.productsdelivery;

import com.example.demo.models.products.Product;
import com.example.demo.models.products.Status;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Table(name = "tbl_product_delivery")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@NamedEntityGraphs({@NamedEntityGraph(name = "graph.WholeProductDelivery", attributeNodes = {
                    @NamedAttributeNode(value = "deliveryHistory"),
                    @NamedAttributeNode(value = "startingAddress"),
                    @NamedAttributeNode(value = "products"),
                    @NamedAttributeNode(value = "deliverySpecification", subgraph = "subgraph.deliverySpecification")},
        subgraphs = {
        @NamedSubgraph(name = "subgraph.deliverySpecification",
                attributeNodes = @NamedAttributeNode(value = "deliveryAddress", subgraph="subgraph.deliveryAddress"))}),

        @NamedEntityGraph(name = "graph.DeliveryWithoutProducts", attributeNodes = {
                @NamedAttributeNode(value = "deliveryHistory"),
                @NamedAttributeNode(value = "startingAddress"),
                @NamedAttributeNode(value = "deliverySpecification", subgraph = "subgraph.deliverySpecification")},
                subgraphs = {
                        @NamedSubgraph(name = "subgraph.deliverySpecification",
                                attributeNodes = @NamedAttributeNode(value = "deliveryAddress", subgraph="subgraph.deliveryAddress"))}),
        @NamedEntityGraph(name = "graph.DeliveryOnlyWithProducts", attributeNodes = {@NamedAttributeNode(value = "products")})}
)
@Entity
public class ProductDelivery implements Serializable {

    @Id
    @SequenceGenerator(
            name = "product_delivery_sequence",
            sequenceName = "product_delivery_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_delivery_sequence"
    )
    @Column(name = "delivery_id")
    private long deliveryId;
    private String description;
    private Double totalWeight;
    private Double temperature;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "deliveryHistoryId")

    @OneToOne(cascade= CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_history_id", referencedColumnName = "delivery_history_id")
    private DeliveryHistory deliveryHistory;

    @OneToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name = "starting_address_id", referencedColumnName = "delivery_address_id")
    private DeliveryAddress startingAddress;

    @OneToOne(cascade= CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_specification_id", referencedColumnName = "delivery_specification_id")
    private DeliverySpecification deliverySpecification;

    @OneToMany(mappedBy = "productDelivery", orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        this.products.add(product);
        product.setProductDelivery(this);
    }

    public void removeProduct(Product product){
        this.products.remove(product);
        product.setProductDelivery(null);
    }

    @JsonManagedReference
    public List<Product> getProducts() {
        return products;
    }

}
