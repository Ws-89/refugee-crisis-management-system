package com.example.demo.models.productsdelivery;

import com.example.demo.models.products.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Double capacity;
    private Double totalWeight;

    @OneToOne(
            cascade=CascadeType.ALL,
            orphanRemoval = true
    )
    private DeliveryHistory deliveryHistory;
    @OneToOne(
            cascade=CascadeType.ALL,
            orphanRemoval = true
    )
    private DeliverySpecification deliverySpecification;
    @JsonIgnore
    @OneToMany(mappedBy = "productDelivery", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();




    public void update(ProductDelivery source) {
        this.description = source.getDescription();
    }


    public void addProduct(Product product){
        product.setProductDelivery(this);
        this.products.add(product);
    }

    public void removeProduct(Product product){
        product.setProductDelivery(null);
        this.products.remove(product);
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

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
