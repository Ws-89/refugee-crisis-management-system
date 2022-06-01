package com.example.demo.models.lodging;

import com.example.demo.models.GenericEntity;
import com.example.demo.models.shared.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_lodging")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lodging implements Serializable, GenericEntity<Lodging> {

    @Id
    @SequenceGenerator(
            name = "tbl_lodging_sequence",
            sequenceName = "tbl_lodging_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tbl_lodging_sequence"
    )
    private Long id;
    private Address address;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "lodging_owner_id",
            referencedColumnName = "lodgingOwnerId"
    )
    private LodgingOwner lodgingOwner;

    @Override
    public void update(Lodging source) {
        this.address = source.getAddress();
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Lodging createNewInstance() {
        Lodging newInstance = new Lodging();
        newInstance.update(this);
        return newInstance;
    }
}
