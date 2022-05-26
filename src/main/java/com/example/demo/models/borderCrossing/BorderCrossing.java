package com.example.demo.models.borderCrossing;

import com.example.demo.models.shared.Address;
import com.example.demo.models.shared.Contact;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "tbl_border_crossing")
@Builder
public class BorderCrossing {

    @Id
    @SequenceGenerator(
            name = "border_crossing_sequence",
            sequenceName = "border_crossing_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "border_crossing_sequence"
    )
    private long id;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;



}
