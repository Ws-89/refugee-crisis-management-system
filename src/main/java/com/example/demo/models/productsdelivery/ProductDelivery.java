package com.example.demo.models.productsdelivery;

import com.example.demo.models.Customer;
import com.example.demo.models.GenericEntity;
import com.example.demo.models.products.Product;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Data
@Entity
@Table(name = "tbl_product_delivery")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDelivery implements Serializable, GenericEntity<ProductDelivery>, Prototype<ProductDelivery> {

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
    @OneToMany(
            mappedBy =  "productDelivery"
    )
    private List<Product> products = new ArrayList<Product>();
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customerId"
    )
    private Customer customer;


    public ProductDelivery(ProductDelivery productDelivery) {
        this.description = productDelivery.description;
        this.capacity = productDelivery.capacity;
        this.deliveryHistory = null;
        this.deliverySpecification = null;
        this.products = null;
        this.customer = productDelivery.customer;
    }

    @Override
    public void update(ProductDelivery source) {
        this.description = source.getDescription();
    }

    @Override
    public Long getId() {
        return this.getDeliveryId();
    }

    @Override
    public ProductDelivery createNewInstance() {
        ProductDelivery newInstance = new ProductDelivery();
        newInstance.update(this);
        return newInstance;
    }

    @Override
    public ProductDelivery clone() {
        return new ProductDelivery(this);
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
}
