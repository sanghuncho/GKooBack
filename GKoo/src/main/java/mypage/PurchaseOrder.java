package mypage;

import java.time.LocalDate;

public class PurchaseOrder {

	private String gkooId;
	/*상품명*/
	private String productName;
	/*상품이미지 url*/
	private String productImageUrl;
	/*상품가격*/
	private int productPrice;
	/*수수료*/
	private int serviceFee;
	/*구매총금액*/
	private int totalPrice;
	/*진행과정 */
	private String status;
	
	public PurchaseOrder(String gkooId, String productName, String productImageUrl, int productPrice, int serviceFee,
			int totalPrice, String status) {
		this.gkooId = gkooId;
		this.productName = productName;
		this.productImageUrl = productImageUrl;
		this.productPrice = productPrice;
		this.serviceFee = serviceFee;
		this.totalPrice = totalPrice;
		this.status = status;
	}

	public PurchaseOrder() {}

	public String getGkooId() {
		return gkooId;
	}

	public void setGkooId(String gkooId) {
		this.gkooId = gkooId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(int serviceFee) {
		this.serviceFee = serviceFee;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
