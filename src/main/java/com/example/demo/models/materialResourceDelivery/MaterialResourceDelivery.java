package com.example.demo.models.materialResourceDelivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_material_resource_delivery")
@AllArgsConstructor
@ToString
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
    private String description;
    @Type(type = "com.example.demo.models.materialResourceDelivery.MaterialResourceDeliveryStateType")
    @Column(name = "delivery_state")
    private MaterialResourceDeliveryState state;

    public MaterialResourceDelivery() {
        this.state
                = new MaterialResourceDeliveryStateStarted();
    }

    public String getStateName(){
        return this.state.getName();
    }

    public void nextState(){
        state.nextState(this);
    }

    public void prevState(){
        state.prevState(this);
    }


}
