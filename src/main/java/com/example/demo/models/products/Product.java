package com.example.demo.models.products;

import com.example.demo.models.productsdelivery.ProductDelivery;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
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
    private LocalDate expirationDate;
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
    private ProductDelivery productDelivery;

    @JsonBackReference
    public ProductDelivery getProductDelivery() {
        return productDelivery;
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
