package com.example.demo.models.products;

import com.example.demo.models.productsdelivery.ProductDelivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "PRODUCT_TYPE",
        discriminatorType = DiscriminatorType.STRING
)

@RequiredArgsConstructor
@Table(name = "tbl_product")
@Data
public abstract class Product implements Serializable {

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
    private LocalDateTime expirationDate;
    private String description;
    private double weight;
    private Long amount;
    private boolean fragile;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "delivery_id",
            referencedColumnName = "deliveryId"
    )
    private ProductDelivery productDelivery;

    public Product(String name, LocalDateTime expirationDate
                   , String description, double weight, Long amount, boolean fragile, State state) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.description = description;
        this.weight = weight;
        this.amount = amount;
        this.fragile = fragile;
        this.state = state;
    }

    public abstract void update(ProductDTO product);

}
