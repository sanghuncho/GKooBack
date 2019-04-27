package shippingService;

public class ShippingProduct {
	private String shopUrl;
	private String easyship;
	
	private String trackingTitle;
	private String trackingNumber;
	private String categoryTitle;

	private String itemTitle;
	private String brandName;
	private String itemName;
	
	private int amount;
	private double price;
	private double totalPrice;
	
	public ShippingProduct() {}
	
	public ShippingProduct(String shopUrl, String easyship, String trackingTitle,
			String trackingNumber, String categoryTitle, String itemTitle,
			String brandName, String itemName, double totalPrice) {
	}
	
	public String getShopUrl() {
		return shopUrl;
	}
	
	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	
	public String getEasyship() {
		return easyship;
	}
	
	public void setEasyship(String easyship) {
		this.easyship = easyship;
	}
	
	public String getTrackingTitle() {
		return trackingTitle;
	}
	
	public void setTrackingTitle(String trackingTitle) {
		this.trackingTitle = trackingTitle;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}
	
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	
	public String getCategoryTitle() {
		return categoryTitle;
	}
	
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	
	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = Double.parseDouble(totalPrice);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = Integer.parseInt(amount);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = Double.parseDouble(price);
	}
}
