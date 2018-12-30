package mypage;

import java.time.LocalDate;

public class EndSettlement {
	private String gkooId;
	private LocalDate date;
	/*입금액*/
	private int transactionMoney;
	/*적립금*/
	private Integer depositMoney;
	private String itemName;
	private String itemImageUrl;
	/*구매총금액*/
	private Integer purchasePrice;
	/*배송비*/
	private Integer shippingPrice;
	/*최종정산금액*/
	private Integer settleAmount;
	
	public EndSettlement(String gkooId, 
			LocalDate date,
			int transactionMoney,
			Integer depositMoney,
			String itemName,
			String itemImageUrl,
			Integer purchasePrice,
			Integer shippingPrice,
			Integer settleAmount ) {
		this.gkooId = gkooId;
		this.date= date;
		this.transactionMoney = transactionMoney;
		this.depositMoney = depositMoney;
		this.itemName = itemName;
		this.itemImageUrl = itemImageUrl;
		this.purchasePrice = purchasePrice;
		this.shippingPrice = shippingPrice;
		this.settleAmount = settleAmount;
	}

	public EndSettlement() {}
	
	public String getGkooId() {
		return this.gkooId;
	}

	public void setGkooId(String gkooId) {
		this.gkooId = gkooId;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getTransactionMoney() {
		return this.transactionMoney;
	}

	public void setTransactionMoney(int transactionMoney) {
		this.transactionMoney = transactionMoney;
	}

	public Integer getDepositMoney() {
		return this.depositMoney;
	}

	public void setDepositMoney(Integer depositMoney) {
		this.depositMoney = depositMoney;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemImageUrl() {
		return this.itemImageUrl;
	}

	public void setItemImageUrl(String itemUrl) {
		this.itemImageUrl = itemUrl;
	}

	public Integer getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Integer getShippingPrice() {
		return this.shippingPrice;
	}

	public void setShippingPrice(Integer shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public Integer getSettleAmount() {
		return this.settleAmount;
	}

	public void setSettleAmount(Integer settleAmount) {
		this.settleAmount = settleAmount;
	}
}
