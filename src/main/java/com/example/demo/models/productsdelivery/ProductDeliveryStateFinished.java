package com.example.demo.models.productsdelivery;

public class ProductDeliveryStateFinished implements ProductDeliveryState {

    private final String name;

    public ProductDeliveryStateFinished() {
        this.name = "Finished";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void nextState(ProductDelivery productDelivery) {
        System.out.println("This delivery has been finished");
    }

    @Override
    public void prevState(ProductDelivery productDelivery) {
        System.out.println("This delivery has been finished");
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
