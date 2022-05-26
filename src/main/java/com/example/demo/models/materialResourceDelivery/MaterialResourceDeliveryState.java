package com.example.demo.models.materialResourceDelivery;

public interface MaterialResourceDeliveryState {

    String getName();
    void nextState(MaterialResourceDelivery materialResourceDelivery);
    void prevState(MaterialResourceDelivery materialResourceDelivery);
}
