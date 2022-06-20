package com.example.demo.models.productsdelivery;

import com.example.demo.models.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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
    private Long id;
    private String postCode;
    private String city;
    private String street;

    public void update(DeliveryAddress source) {
        this.setPostCode(source.getPostCode());
        this.setCity(source.getCity());
        this.setStreet(source.getStreet());
    }
}
