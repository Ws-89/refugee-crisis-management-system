package com.example.demo.models;

import com.example.demo.models.shared.Address;
import com.example.demo.models.shared.Contact;
import com.example.demo.models.shared.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name= "tbl_refugee"
)
public class Refugee implements Serializable, GenericEntity<Refugee> {

    @Id
    @SequenceGenerator(
            name="refugee_sequence",
            sequenceName="refugee_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator= "refugee_sequence"
    )
    private long id;
    @Embedded
    private Person person;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;

    @Override
    public Long getId(){
        return this.id;
    }

    @Override
    public void update(Refugee source) {
        this.person = source.getPerson();
        this.address = source.getAddress();
        this.contact = source.getContact();
    }

    @Override
    public Refugee createNewInstance() {
        Refugee newInstance = new Refugee();
        newInstance.update(this);
        return newInstance;
    }
}