package com.example.demo.models.lodging;

import com.example.demo.models.GenericEntity;
import com.example.demo.models.Refugee;
import com.example.demo.models.shared.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tbl_lodging")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Long lodgingId;
    private Address address;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "lodging_owner_id",
            referencedColumnName = "lodgingOwnerId"
    )
    private LodgingOwner lodgingOwner;
    @Enumerated(value = EnumType.STRING)
    private LodgingState state;
    private Integer capacity;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "lodging_id",
            referencedColumnName = "lodgingId"
    )
    private List<Refugee> tenants;

    @Override
    public void update(Lodging source) {
        this.address = source.getAddress();
    }

    public Long getId() {
        return this.lodgingId;
    }

    @Override
    public Lodging createNewInstance() {
        Lodging newInstance = new Lodging();
        newInstance.update(this);
        return newInstance;
    }

    public void setStateToAvailable(){
        this.state = LodgingState.Available;
    }

    public void setStateToCanceled(){
        this.tenants.clear();
        this.state = LodgingState.Canceled;
    }

    public void addTentant(Refugee refugee){
        if(tenants.size() < capacity)
            tenants.add(refugee);

        if(tenants.size() == capacity)
            this.state = LodgingState.Occupied;
    }

    public void removeTenant(Long id){
        if(this.tenants.contains(id))
            this.tenants.remove(id);

        if(this.state != LodgingState.Available)
            this.setStateToAvailable();
    }
}
