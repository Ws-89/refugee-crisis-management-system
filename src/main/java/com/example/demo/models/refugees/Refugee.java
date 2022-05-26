package com.example.demo.models.refugees;

import com.example.demo.models.shared.Address;
import com.example.demo.models.shared.Contact;
import com.example.demo.models.shared.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name= "tbl_refugee"
)
public class Refugee {

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


}