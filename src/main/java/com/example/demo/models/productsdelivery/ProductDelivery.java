package com.example.demo.models.productsdelivery;

import com.example.demo.models.products.Product;
import com.example.demo.models.products.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Table(name = "tbl_product_delivery")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
    private long deliveryId;
    private String description;
    private Double totalWeight;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToOne(cascade= CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "delivery_history_id", referencedColumnName = "deliveryHistoryId")
    private DeliveryHistory deliveryHistory;

    @OneToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name = "starting_address_id", referencedColumnName = "deliveryAddressId")
    private DeliveryAddress startingAddress;

    @OneToOne(cascade= CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "delivery_specification_id", referencedColumnName = "deliverySpecificationId")
    private DeliverySpecification deliverySpecification;


    @OneToMany(mappedBy = "productDelivery", orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

    public void update(ProductDelivery source) {
        this.description = source.getDescription();
    }

    public void addProduct(Product product){
        this.products.add(product);
        product.setProductDelivery(this);
    }

    public void removeProduct(Product product){
        this.products.remove(product);
        product.setProductDelivery(null);
    }

    public long getDeliveryId() {
        return deliveryId;
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

    public DeliveryHistory getDeliveryHistory() {
        return deliveryHistory;
    }

    public void setDeliveryHistory(DeliveryHistory deliveryHistory) {
        this.deliveryHistory = deliveryHistory;
    }

    public DeliverySpecification getDeliverySpecification() {
        return deliverySpecification;
    }

    public void setDeliverySpecification(DeliverySpecification deliverySpecification) {
        this.deliverySpecification = deliverySpecification;
    }
    @JsonManagedReference
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void setDeliveryId(long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DeliveryAddress getStartingAddress() {
        return startingAddress;
    }

    public void setStartingAddress(DeliveryAddress startingAddress) {
        this.startingAddress = startingAddress;
    }
}
