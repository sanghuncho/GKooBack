package payment;

import java.io.Serializable;
import java.util.Date;

public abstract class PaymentBase implements IPayment, Serializable {

    private static final long serialVersionUID = 2872336983490436485L;
    protected int paymentid;
	protected String orderid;
	protected Date paymentDate;
	protected String paymentOwnername;
	protected int paymentArt;
	protected double paymentDeposit;
	protected int paymentState;
	protected int fk_orderstate;
	
	public PaymentBase() {
	  
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
