package com.gkoo.data;

/**
 * @author sanghun cho
 *
 */
public class DeliveryKoreaData {
    private int id;
    private String orderid;
    private int deliveryState;
    private String deliveryTracking;
    
    public DeliveryKoreaData() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public int getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(int deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryTracking() {
        return deliveryTracking;
    }

    public void setDeliveryTracking(String deliveryTracking) {
        this.deliveryTracking = deliveryTracking;
    }
}
