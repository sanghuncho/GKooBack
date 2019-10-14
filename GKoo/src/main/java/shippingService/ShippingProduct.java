package shippingService;

public class ShippingProduct {
	
	private String categoryTitle;
	private String itemTitle;
	private String brandName;
	private String itemName;
	
	private int amount;
	private double price;
	private double totalPrice;
	
	public ShippingProduct() {}
	
	public ShippingProduct(String categoryTitle, String itemTitle,
			String brandName, String itemName, double totalPrice) {
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
