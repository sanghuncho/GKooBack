package mypage.information;

import java.util.ArrayList;
import java.util.List;

public class ProductsInformation {

	private List<Product> productsList =  new ArrayList<Product>();
	
	public ProductsInformation() {}

	public List<Product> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}
	
	public Product createProduct() {
		return new ProductsInformation.Product();
	}
	
	public class Product {
		
		private String categorytitle;
		private String itemtitle;
		private String brandname;
		private String itemname;
		private int amount;
		private double price;
		private double totalPrice;
		
		public Product() {}
		
		public Product(String category, String itemTitle, String brandName, String itemName,
				int amount, double price, double totalPrice) {
			this.categorytitle = category;
			this.itemtitle = itemTitle;
			this.brandname = brandName;
			this.itemname = itemName;
			this.amount = amount;
			this.price = price;
			this.totalPrice = totalPrice;
		}
		
		public String getCategorytitle() {
			return categorytitle;
		}

		public void setCategorytitle(String categorytitle) {
			this.categorytitle = categorytitle;
		}

		public String getItemtitle() {
			return itemtitle;
		}

		public void setItemtitle(String itemtitle) {
			this.itemtitle = itemtitle;
		}

		public String getBrandname() {
			return brandname;
		}

		public void setBrandname(String brandname) {
			this.brandname = brandname;
		}

		public String getItemName() {
			return itemname;
		}

		public void setItemName(String itemname) {
			this.itemname = itemname;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}
	}
}
