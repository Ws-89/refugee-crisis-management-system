package com.example.demo.models.productsdelivery;

public class ProductDeliveryStateCanceled implements ProductDeliveryState {

    private final String name;

    public ProductDeliveryStateCanceled() {
        this.name = "Canceled";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void nextState(ProductDelivery productDelivery) {
        System.out.println("This delivery has been canceled");
    }

    @Override
    public void prevState(ProductDelivery productDelivery) {
        System.out.println("This delivery has been canceled");
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
