package com.example.demo.models.materialResourceDelivery;

public class MaterialResourceDeliveryStateFinished implements MaterialResourceDeliveryState {

    private final String name;

    public MaterialResourceDeliveryStateFinished() {
        this.name = "Finished";
    }

    @Override
    public String getName() {
        return name;
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
