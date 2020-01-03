package payment;

public class PaymentData {
    private int paymentid;
    private String orderid;
    private int paymentState;
    
    public PaymentData() {
    }
    
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
}
