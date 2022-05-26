package com.example.demo.models.materialResourceDelivery;

public class MaterialResourceDeliveryStateCanceled implements MaterialResourceDeliveryState {

    public static final String stateName = "Canceled";
    private final String name;

    public MaterialResourceDeliveryStateCanceled() {
        this.name = "Canceled";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void nextState(MaterialResourceDelivery materialResourceDelivery) {
        System.out.println("This delivery has been canceled");
    }

    @Override
    public void prevState(MaterialResourceDelivery materialResourceDelivery) {
        System.out.println("This delivery has been canceled");
    }



}
