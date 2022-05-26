package com.example.demo.models.materialResourceDelivery;

import javax.persistence.Embeddable;

@Embeddable
public class MaterialResourceDeliveryStateStarted implements MaterialResourceDeliveryState {

    public static final String stateName = "Started";
    private final String name;

    public MaterialResourceDeliveryStateStarted() {
        this.name = "Rejected";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void nextState(MaterialResourceDelivery materialResourceDelivery) {
        materialResourceDelivery.setState(new MaterialResourceDeliveryStatePrepared());
    }

    @Override
    public void prevState(MaterialResourceDelivery materialResourceDelivery) {
        materialResourceDelivery.setState(new MaterialResourceDeliveryStateCanceled());
    }
}
