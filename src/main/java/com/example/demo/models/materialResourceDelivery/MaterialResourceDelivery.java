package com.example.demo.models.materialResourceDelivery;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_material_resource_delivery")
public class MaterialResourceDelivery {


    @Id
    @SequenceGenerator(
            name = "material_resource_delivery_sequence",
            sequenceName = "material_resource_delivery_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "material_resource_delivery_sequence"
    )
    private long deliveryId;

    @Type(type = "com.example.demo.models.materialResourceDelivery.MaterialResourceDeliveryStateType")
    @Column(name = "delivery_state")
    private MaterialResourceDeliveryState state;

    public MaterialResourceDelivery() {
        this.state = new MaterialResourceDeliveryStateStarted();
    }

    public void nextState(){
        state.nextState(this);
    }

    public void prevState(){
        state.prevState(this);
    }

}
