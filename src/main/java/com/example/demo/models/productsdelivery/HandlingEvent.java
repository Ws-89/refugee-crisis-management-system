package com.example.demo.models.productsdelivery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "transport_movement_id",
            referencedColumnName = "transportMovementId"
    )
    private TransportMovement transportMovement;
    @ManyToOne(
            cascade = {CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "delivery_history_id",
            referencedColumnName = "deliveryHistoryId"
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DeliveryHistory deliveryHistory;
    @Enumerated(value = EnumType.STRING)
    private HandlingEventState state;
    private LocalDateTime timeStamp;

    public HandlingEvent(TransportMovement transportMovement, HandlingEventState state, LocalDateTime timeStamp) {
        this.transportMovement = transportMovement;
        this.state = state;
        this.timeStamp = timeStamp;
    }

    public static HandlingEvent newLoading(TransportMovement loadedOnto, LocalDateTime timeStamp) {
        HandlingEvent result = new HandlingEvent(loadedOnto, LOADING_EVENT, timeStamp);
        return result;
    }

    public static HandlingEvent newUnload(TransportMovement unloadedFrom, LocalDateTime timeStamp) {
        HandlingEvent result = new HandlingEvent(unloadedFrom, UNLOADING_EVENT, timeStamp);
        return result;
    }

    private void setTransportMovement(TransportMovement loadedOnto) {
    }

    public void update(HandlingEventDTO source){
//        this.setHandlingEventId(source.getHandlingEventId());
        this.setTransportMovement(source.getTransportMovement());
        this.setDeliveryHistory(source.getDeliveryHistory());
        this.setState(source.getState());
        this.setTimeStamp(source.getTimeStamp());
    }


}
