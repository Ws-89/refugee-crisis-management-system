package com.example.demo.models.products;

import com.example.demo.models.productsdelivery.ProductDelivery;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;



@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_product")
@Builder
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
    @Column(name = "product_id")
    private Long productId;
    private String name;
    private LocalDateTime expirationDate;
    private String description;
    private double weight;
    private Long amount;
    @Enumerated(EnumType.STRING)
    private Status reserved;
    private boolean fragile;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @Embedded
    private Category category;

    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", referencedColumnName = "delivery_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ProductDelivery productDelivery;


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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    @JsonBackReference
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
