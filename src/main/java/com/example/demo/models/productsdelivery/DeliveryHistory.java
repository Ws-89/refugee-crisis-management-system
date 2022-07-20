package com.example.demo.models.productsdelivery;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_delivery_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryHistory {

    @Id
    @SequenceGenerator(
            name = "delivery_history_sequence",
            sequenceName = "delivery_history_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "delivery_history_sequence"
    )
    @Column(name = "delivery_history_id")
    private long deliveryHistoryId;
    @ManyToMany(
            mappedBy = "wayBills", cascade = CascadeType.MERGE
    )
    Set<TransportMovement> transportMovements;
    @OneToOne(
            mappedBy = "deliveryHistory", cascade = CascadeType.MERGE
    )
    private ProductDelivery productDelivery;
    @OneToMany(mappedBy = "deliveryHistory", orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<CargoActivity> cargoActivityList;
    private Long finalDestinationId;
    private Boolean isLoaded;
    private long currentTransportMovementId;
    public long getDeliveryHistoryId() {
        return deliveryHistoryId;
    }

    public void setDeliveryHistoryId(long deliveryHistoryId) {
        this.deliveryHistoryId = deliveryHistoryId;
    }

    public Set<TransportMovement> getTransportMovements() {
        return transportMovements;
    }

    public void setTransportMovements(Set<TransportMovement> transportMovements) {
        this.transportMovements = transportMovements;
    }

    public ProductDelivery getProductDelivery() {
        return productDelivery;
    }

    public void setProductDelivery(ProductDelivery productDelivery) {
        this.productDelivery = productDelivery;
    }

    public Long getFinalDestinationId() {
        return finalDestinationId;
    }

    public void setFinalDestinationId(Long finalDestinationId) {
        this.finalDestinationId = finalDestinationId;
    }

    public List<CargoActivity> getCargoActivityList() {
        return cargoActivityList;
    }

    public void setCargoActivityList(List<CargoActivity> cargoActivityList) {
        this.cargoActivityList = cargoActivityList;
    }

    public Boolean getIsLoaded() {
        return isLoaded;
    }

    public void setIsLoaded(Boolean loaded) {
        isLoaded = loaded;
    }

    public long getCurrentTransportMovementId() {
        return currentTransportMovementId;
    }

    public void setCurrentTransportMovementId(long currentTransportMovementId) {
        this.currentTransportMovementId = currentTransportMovementId;
    }
}
