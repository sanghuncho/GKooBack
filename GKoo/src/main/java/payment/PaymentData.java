package payment;

import java.util.Date;

public class PaymentData {
    private int paymentid;
    private String orderid;
    private int paymentState;
    private double buyingPrice;
    private double shipPrice;
    private double boxActualWeight;
    private double boxVolumeWeight;
    private String paymentOwnername;
    private Date paymentDate;
    private int paymentArt;
    
    public String getPaymentOwnername() {
        return paymentOwnername;
    }

    public void setPaymentOwnername(String paymentOwnername) {
        if (paymentOwnername == null) {
            this.paymentOwnername = "";
        } else {
            this.paymentOwnername = paymentOwnername;
        }
    }

    public PaymentData() { }
    
    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }
    
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    
    public void setPaymentState(int paymentState) {
        this.paymentState = paymentState;
    }
    
    public String getOrderid() {
        return orderid;
    }
    
    public int getPaymentid() {
        return paymentid;
    }
    
    public int getPaymentState() {
        return paymentState;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPaymentArt() {
        return paymentArt;
    }

    public void setPaymentArt(int paymentArt) {
        this.paymentArt = paymentArt;
    }

    public double getShipPrice() {
        return shipPrice;
    }

    public void setShipPrice(double shipPrice) {
        this.shipPrice = shipPrice;
    }

    public double getBoxActualWeight() {
        return boxActualWeight;
    }

    public void setBoxActualWeight(double boxActualWeight) {
        this.boxActualWeight = boxActualWeight;
    }

    public double getBoxVolumeWeight() {
        return boxVolumeWeight;
    }

    public void setBoxVolumeWeight(double boxVolumeWeight) {
        this.boxVolumeWeight = boxVolumeWeight;
    }

}