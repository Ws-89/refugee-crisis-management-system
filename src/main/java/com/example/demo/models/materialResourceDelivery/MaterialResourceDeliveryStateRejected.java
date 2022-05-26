package com.example.demo.models.materialResourceDelivery;

public class MaterialResourceDeliveryStateRejected implements MaterialResourceDeliveryState {

    public static final String stateName = "Rejected";
    private final String name;

    public MaterialResourceDeliveryStateRejected() {
        this.name = "Rejected";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void nextState(MaterialResourceDelivery materialResourceDelivery) {
        materialResourceDelivery.setState(new MaterialResourceDeliveryStateStarted());
    }

    @Override
    public void prevState(MaterialResourceDelivery materialResourceDelivery) {
        materialResourceDelivery.setState(new MaterialResourceDeliveryStateCanceled());
    }


}
