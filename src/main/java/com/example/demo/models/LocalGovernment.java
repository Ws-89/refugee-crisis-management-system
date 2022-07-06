package com.example.demo.models;

import com.example.demo.models.shared.Address;
import com.example.demo.models.shared.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_local_government")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalGovernment implements Serializable, GenericEntity<LocalGovernment> {

    @Id
    @SequenceGenerator(
            name = "local_government_sequence",
            sequenceName = "local_government_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "local_government_sequence"
    )
    private Long id;
    private Address address;
    private Contact contact;

    @Override
    public void update(LocalGovernment source) {
        this.address = source.getAddress();
        this.contact = source.getContact();
    }

    @Override
    public Long getId(){
        return this.id;
    }

    @Override
    public LocalGovernment createNewInstance() {
        LocalGovernment newInstance = new LocalGovernment();
        newInstance.update(this);
        return newInstance;
    }
}
