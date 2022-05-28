package com.example.demo.models.materialResourceDelivery;

public class MaterialResourceDeliveryStatePrepared implements MaterialResourceDeliveryState {

    private final String name;

    public MaterialResourceDeliveryStatePrepared() {
        this.name = "Prepared";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void nextState(MaterialResourceDelivery materialResourceDelivery) {
        materialResourceDelivery.setState(new MaterialResourceDeliveryStateInTransition());
    }

    @Override
    public void prevState(MaterialResourceDelivery materialResourceDelivery) {
        materialResourceDelivery.setState(new MaterialResourceDeliveryStateRejected());
    }
}
