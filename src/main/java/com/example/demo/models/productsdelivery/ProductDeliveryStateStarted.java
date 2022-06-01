package com.example.demo.models.productsdelivery;

public class ProductDeliveryStateStarted implements ProductDeliveryState {

    private final String name;

    public ProductDeliveryStateStarted() {
        this.name = "Started";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void nextState(ProductDelivery productDelivery) {
        productDelivery.setState(new ProductDeliveryStatePrepared());
    }

    @Override
    public void prevState(ProductDelivery productDelivery) {
        productDelivery.setState(new ProductDeliveryStateCanceled());
    }

    @Override
    public String getNextStateMessageText() {
        return null;
    }

    @Override
    public String getPrevStateMessageText() {
        return null;
    }
}
