package payment;

public abstract class PaymentBase implements IPayment {
  
	protected String orderId;
	protected String paymentOwnername;
	protected int paymentState;
	
	public PaymentBase() {
	  
	}
}
