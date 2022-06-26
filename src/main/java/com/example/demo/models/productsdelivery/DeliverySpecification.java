package com.example.demo.models.productsdelivery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Long deliverySpecificationId;
    private LocalDateTime arrivalTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "delivery_address_id", referencedColumnName = "deliveryAddressId")
    private DeliveryAddress deliveryAddress;
    @JsonIgnore
    @OneToOne(mappedBy = "deliverySpecification")
    private ProductDelivery productDelivery;

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

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public ProductDelivery getProductDelivery() {
        return productDelivery;
    }

    public void setProductDelivery(ProductDelivery productDelivery) {
        this.productDelivery = productDelivery;
    }
}
