package com.example.demo.models.materialResourceDelivery;

public class MaterialResourceDeliveryStateStarted implements MaterialResourceDeliveryState {

    private final String name;

    public MaterialResourceDeliveryStateStarted() {
        this.name = "Started";
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
