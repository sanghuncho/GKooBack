package com.gkoo.data;

public class EstimationService {
    private double resultPrice;
    private boolean mergeBox;
    private boolean inputDeliveryFee;
    
    public EstimationService() {}

    public double getResultPrice() {
        return resultPrice;
    }

    public void setResultPrice(double resultPrice) {
        this.resultPrice = resultPrice;
    }

    public boolean isInputDeliveryFee() {
        return inputDeliveryFee;
    }

    public void setInputDeliveryFee(boolean inputDeliveryFee) {
        this.inputDeliveryFee = inputDeliveryFee;
    }
}
