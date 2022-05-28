package com.example.demo.models;

import com.example.demo.models.shared.Address;
import com.example.demo.models.shared.Contact;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_border_crossing")
@Builder
@Data
public class BorderCrossing implements Serializable, GenericEntity<BorderCrossing> {

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


    @Override
    public void update(BorderCrossing source) {
        this.address = source.getAddress();
        this.contact = source.getContact();
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public BorderCrossing createNewInstance() {
        return null;
    }
}
