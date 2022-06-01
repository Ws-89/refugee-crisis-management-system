package com.example.demo.models.productsdelivery;

public class ProductDeliveryStateRejected implements ProductDeliveryState {

    private final String name;

    public ProductDeliveryStateRejected() {
        this.name = "Rejected";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void nextState(ProductDelivery productDelivery) {
        productDelivery.setState(new ProductDeliveryStateStarted());
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
