package com.example.demo.models.productsdelivery;

import com.example.demo.models.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Long deliveryAddressId;
    private String postCode;
    private String city;
    private String street;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryAddress")
    private Set<DeliverySpecification> deliverySpecifications = new HashSet<>();

    public void update(DeliveryAddress source) {
        this.setPostCode(source.getPostCode());
        this.setCity(source.getCity());
        this.setStreet(source.getStreet());
    }

    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Set<DeliverySpecification> getDeliverySpecifications() {
        return deliverySpecifications;
    }

    public void setDeliverySpecifications(Set<DeliverySpecification> deliverySpecifications) {
        this.deliverySpecifications = deliverySpecifications;
    }
}
