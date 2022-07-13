package com.example.demo.models.productsdelivery;

import com.example.demo.models.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_delivery_address")
@Entity
public class DeliveryAddress {

    @Id
    @SequenceGenerator(
            name = "delivery_address_sequence",
            sequenceName = "delivery_address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "delivery_address_sequence"
    )
    @Column(name = "delivery_address_id")
    private Long deliveryAddressId;
    private String postCode;
    private String city;
    private String street;
    private String state;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryAddress", cascade = CascadeType.PERSIST)
    private Set<DeliverySpecification> deliverySpecifications = new HashSet<>();


    public void addDeliverySpecification(DeliverySpecification deliverySpecification) {
        this.deliverySpecifications.add(deliverySpecification);
        deliverySpecification.setDeliveryAddress(this);
    }

    public void removeDeliverySpecification(DeliverySpecification deliverySpecification) {
        this.deliverySpecifications.remove(deliverySpecification);
        deliverySpecification.setDeliveryAddress(null);
    }

}