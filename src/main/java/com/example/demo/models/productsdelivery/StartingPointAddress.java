package com.example.demo.models.productsdelivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_starting_point_address")
@Entity
public class StartingPointAddress {

    @Id
    @SequenceGenerator(
            name = "starting_point_address_sequence",
            sequenceName = "starting_point_address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "starting_point_address_sequence"
    )
    private Long id;
    private String postCode;
    private String city;

}