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
@Table(name = "tbl_address")
@Entity
public class Address {

    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    @Column(name = "address_id")
    private Long addressId;
    private String postCode;
    private String city;
    private String street;
    private String state;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryAddress", cascade = CascadeType.MERGE)
    private Set<DeliverySpecification> deliverySpecifications = new HashSet<>();


    public void addDeliverySpecification(DeliverySpecification deliverySpecification) {
        this.deliverySpecifications.add(deliverySpecification);
        deliverySpecification.setDeliveryAddress(this);
    }

    public void removeDeliverySpecification(DeliverySpecification deliverySpecification) {
        this.deliverySpecifications.remove(deliverySpecification);
        deliverySpecification.setDeliveryAddress(null);
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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