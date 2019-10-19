package shippingService;

public class ShippingProduct {
	private String categoryTitle;
	private String itemTitle;
	private String brandName;
	private String itemName;
	
	private int productAmount;
	private double productPrice;
	private double productTotalPrice;
	
	public ShippingProduct() {}
	
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
	public double getProductTotalPrice() {
		return productTotalPrice;
	}
	
	public void setProductTotalPrice(String totalPrice) {
		this.productTotalPrice = Double.parseDouble(totalPrice);
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(String amount) {
		this.productAmount = Integer.parseInt(amount);
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String price) {
		this.productPrice = Double.parseDouble(price);
	}
}