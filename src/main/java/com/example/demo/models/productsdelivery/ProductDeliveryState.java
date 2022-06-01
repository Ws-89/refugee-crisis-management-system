package com.example.demo.models.productsdelivery;

public interface ProductDeliveryState {

    String getName();
    void nextState(ProductDelivery productDelivery);
    void prevState(ProductDelivery productDelivery);
    String getNextStateMessageText();
    String getPrevStateMessageText();
}
