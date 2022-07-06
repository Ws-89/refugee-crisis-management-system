package com.example.demo.models.productsdelivery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Long delivery_specification_id;
    private LocalDateTime arrivalTime;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id", referencedColumnName = "delivery_address_id")
    private DeliveryAddress deliveryAddress;
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(mappedBy = "deliverySpecification", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProductDelivery productDelivery;

    public Long getDeliverySpecificationId() {
        return delivery_specification_id;
    }

    public void setDeliverySpecificationId(Long deliverySpecificationId) {
        this.delivery_specification_id = deliverySpecificationId;
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
