package com.example.demo.models.productsdelivery;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_delivery_status_message")
public class DeliveryStatusMessage {

    @SequenceGenerator(
            name = "delivery_status_message_sequence",
            sequenceName = "delivery_status_message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "delivery_status_message_sequence"
    )
    @Id
    private Long id;
    private String message;
    private LocalDateTime postDate;

    public DeliveryStatusMessage(String message, LocalDateTime postDate) {
        this.message = message;
        this.postDate = postDate;
    }
}
