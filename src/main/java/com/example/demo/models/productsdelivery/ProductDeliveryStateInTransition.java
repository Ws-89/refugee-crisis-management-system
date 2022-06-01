package com.example.demo.models.productsdelivery;

public class ProductDeliveryStateInTransition implements ProductDeliveryState {

    private final String name;

    public ProductDeliveryStateInTransition() {
        this.name = "InTransition";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void nextState(ProductDelivery productDelivery) {
        productDelivery.setState(new ProductDeliveryStateFinished());
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
