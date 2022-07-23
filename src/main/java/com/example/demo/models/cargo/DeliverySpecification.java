package com.example.demo.models.cargo;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tbl_delivery_specification")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliverySpecification {

    @Id
    @SequenceGenerator(
            name = "delivery_specification_sequence",
            sequenceName = "delivery_specification_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "delivery_specification_sequence"
    )
    @Column(name = "delivery_specification_id")
    private Long deliverySpecificationId;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address deliveryAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliverySpecification that = (DeliverySpecification) o;
        return arrivalTime.equals(that.arrivalTime) && deliveryAddress.equals(that.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivalTime, deliveryAddress);
    }

    public Long getDeliverySpecificationId() {
        return deliverySpecificationId;
    }

    public void setDeliverySpecificationId(Long deliverySpecificationId) {
        this.deliverySpecificationId = deliverySpecificationId;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
