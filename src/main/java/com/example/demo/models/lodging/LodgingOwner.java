package com.example.demo.models.lodging;

import com.example.demo.models.GenericEntity;
import com.example.demo.models.shared.Contact;
import com.example.demo.models.shared.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_lodging_owner")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LodgingOwner implements Serializable, GenericEntity<LodgingOwner> {

    @Id
    @SequenceGenerator(
            name = "tbl_lodging_owner",
            sequenceName = "tbl_lodging_owner_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tbl_lodging_owner"
    )
    private Long lodgingOwnerId;
    @Embedded
    private Person person;
    @Embedded
    private Contact contact;


    @Override
    public void update(LodgingOwner source) {
        this.person = source.getPerson();
        this.contact = source.getContact();
    }

    public Long getId() {
        return this.lodgingOwnerId;
    }

    @Override
    public LodgingOwner createNewInstance() {
        LodgingOwner newInstance = new LodgingOwner();
        newInstance.update(this);
        return newInstance;
    }
}
