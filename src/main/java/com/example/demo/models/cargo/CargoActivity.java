package com.example.demo.models.cargo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_cargo_activity")
public class CargoActivity {

    @Id
    @SequenceGenerator(
            name = "tbl_warehouse_action_generator",
            sequenceName = "tbl_warehouse_action_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tbl_warehouse_action_generator"
    )
    @Column(name = "cargo_activity_id")
    private Long cargoActivityId;
    private LocalDateTime timeStamp;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;
    @Enumerated(value = EnumType.STRING)
    private CargoActivityCategory cargoActivityCategory;
    @NotNull
    private Long transportMovementId;
    @ManyToOne(
            cascade = CascadeType.MERGE)
    @JoinColumn(name = "delivery_history_id", referencedColumnName = "delivery_history_id")
    private DeliveryHistory deliveryHistory;

    public Long getCargoActivityId() {
        return cargoActivityId;
    }

    public void setCargoActivityId(Long cargoActivityId) {
        this.cargoActivityId = cargoActivityId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CargoActivityCategory getCargoActivityCategory() {
        return cargoActivityCategory;
    }

    public void setCargoActivityCategory(CargoActivityCategory cargoActivityCategory) {
        this.cargoActivityCategory = cargoActivityCategory;
    }

    public Long getTransportMovementId() {
        return transportMovementId;
    }

    public void setTransportMovementId(Long transportMovementId) {
        this.transportMovementId = transportMovementId;
    }

    public DeliveryHistory getDeliveryHistory() {
        return deliveryHistory;
    }

    public void setDeliveryHistory(DeliveryHistory deliveryHistory) {
        this.deliveryHistory = deliveryHistory;
    }
}
