package com.example.demo.models.materialResourceDelivery;

import javax.persistence.Embeddable;

public class MaterialResourceDeliveryStateFinished implements MaterialResourceDeliveryState {

    public static final String stateName = "Finished";
    private final String name;

    public MaterialResourceDeliveryStateFinished() {
        this.name = "Finished";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void nextState(MaterialResourceDelivery materialResourceDelivery) {
        System.out.println("This delivery has been finished");
    }

    @Override
    public void prevState(MaterialResourceDelivery materialResourceDelivery) {
        System.out.println("This delivery has been finished");
    }

}
