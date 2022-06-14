package com.example.demo.models;

import com.example.demo.models.productsdelivery.ProductDelivery;

import javax.persistence.*;
import java.util.List;

@Table(name = "tbl_customer")
@Entity
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long customerId;
    @OneToMany(
            mappedBy = "customer"
    )
    private List<ProductDelivery> productDeliveryList;

}
