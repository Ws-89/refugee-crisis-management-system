package com.example.demo.models.productsdelivery;

public class ProductDeliveryStatePrepared implements ProductDeliveryState {

    private final String name;

    public ProductDeliveryStatePrepared() {
        this.name = "Prepared";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void nextState(ProductDelivery productDelivery) {
        productDelivery.setState(new ProductDeliveryStateInTransition());
    }

    @Override
    public void prevState(ProductDelivery productDelivery) {
        productDelivery.setState(new ProductDeliveryStateRejected());
    }

    @Override
    public String getNextStateMessageText(){
        return "";
    }

    @Override
    public String getPrevStateMessageText() {
        return null;
    }
}
