package com.example.demo.models.cargo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<DeliverySpecification> getDeliverySpecifications() {
        return deliverySpecifications;
    }

    public void setDeliverySpecifications(Set<DeliverySpecification> deliverySpecifications) {
        this.deliverySpecifications = deliverySpecifications;
    }
}