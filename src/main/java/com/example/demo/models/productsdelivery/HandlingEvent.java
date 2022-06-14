package com.example.demo.models.productsdelivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

import static com.example.demo.models.productsdelivery.HandlingEventState.LOADING_EVENT;
import static com.example.demo.models.productsdelivery.HandlingEventState.UNLOADING_EVENT;

@Entity
@Table(name = "tbl_handling_event")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HandlingEvent {

    @Id
    @SequenceGenerator(
            name = "handling_event_sequence",
            sequenceName = "handling_event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "handling_event_sequence"
    )
    private long handlingEventId;
    @OneToOne()
    private DeliveryMovement deliveryMovement;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "delivery_history_id",
            referencedColumnName = "deliveryHistoryId"
    )
    private DeliveryHistory deliveryHistory;
    @Enumerated(value = EnumType.STRING)
    private HandlingEventState state;
    private LocalDateTime timeStamp;

    public HandlingEvent(DeliveryMovement deliveryMovement, HandlingEventState state, LocalDateTime timeStamp) {
        this.deliveryMovement = deliveryMovement;
        this.state = state;
        this.timeStamp = timeStamp;
    }

    public static HandlingEvent newLoading(DeliveryMovement loadedOnto, LocalDateTime timeStamp) {
        HandlingEvent result = new HandlingEvent(loadedOnto, LOADING_EVENT, timeStamp);
        result.setDeliveryMovement(loadedOnto);
        return result;
    }

    public static HandlingEvent newUnload(LocalDateTime timeStamp) {
        HandlingEvent result = new HandlingEvent(null, UNLOADING_EVENT, timeStamp);
        return result;
    }

    private void setDeliveryMovement(DeliveryMovement loadedOnto) {
    }


}
