package com.example.demo.models.products;

import com.example.demo.models.productsdelivery.ProductDelivery;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_product")
@Entity
public class Product implements Serializable {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long productId;
    private String name;
    private String category;
    private LocalDateTime expirationDate;
    private String description;
    private double weight;
    private Long amount;
    @Enumerated(value = EnumType.STRING)
    private Status reserved;
    private boolean fragile;
    @Enumerated(value = EnumType.STRING)
    private State state;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", referencedColumnName = "deliveryId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ProductDelivery productDelivery;

    public Product(String name, String category, LocalDateTime expirationDate
                   , String description, double weight, Long amount, Status reserved, boolean fragile, State state) {
        this.name = name;
        this.category = category;
        this.expirationDate = expirationDate;
        this.description = description;
        this.weight = weight;
        this.amount = amount;
        this.reserved = reserved;
        this.fragile = fragile;
        this.state = state;
    }

    public void update(ProductDTO product){
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = product.getProductType();
        this.expirationDate = product.getExpirationDate();
        this.weight = product.getWeight();
        this.amount = product.getAmount();
        this.reserved = product.getReserved();
        this.fragile = product.isFragile();
        this.state = product.getState();
    };

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Status getReserved() {
        return reserved;
    }

    public void setReserved(Status reserved) {
        this.reserved = reserved;
    }

    public boolean isFragile() {
        return fragile;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ProductDelivery getProductDelivery() {
        return productDelivery;
    }

    public void setProductDelivery(ProductDelivery productDelivery) {
        this.productDelivery = productDelivery;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", expirationDate=" + expirationDate +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", amount=" + amount +
                ", reserved=" + reserved +
                ", fragile=" + fragile +
                ", state=" + state +
                ", productDelivery=" + productDelivery +
                '}';
    }
}
