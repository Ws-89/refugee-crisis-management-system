package com.example.demo.models.materialResourceDelivery;

public class MaterialResourceDeliveryStateInTransition implements MaterialResourceDeliveryState{

    public static final String stateName = "InTransition";
    private final String name;

    public MaterialResourceDeliveryStateInTransition() {
        this.name = "InTransition";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void nextState(MaterialResourceDelivery materialResourceDelivery) {
        materialResourceDelivery.setState(new MaterialResourceDeliveryStateFinished());
    }

    @Override
    public void prevState(MaterialResourceDelivery materialResourceDelivery) {
        materialResourceDelivery.setState(new MaterialResourceDeliveryStateCanceled());
    }
}
