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
		
		private String categoryTitle;
		private String itemTitle;
		private String brandName;
		private String itemName;
		private int amount;
		private double price;
		private double totalPrice;
		private String imageUrl;
		
		public Product() {}
		
		public Product(String category, String itemTitle, String brandName, String itemName,
				int amount, double price, double totalPrice, String imageUrl) {
			this.categoryTitle = category;
			this.itemTitle = itemTitle;
			this.brandName = brandName;
			this.itemName = itemName;
			this.amount = amount;
			this.price = price;
			this.totalPrice = totalPrice;
			this.setImageUrl(imageUrl);
		}
		
		public String getCategoryTitle() {
			return categoryTitle;
		}

		public void setCategoryTitle(String categorytitle) {
			this.categoryTitle = categorytitle;
		}

		public String getItemTitle() {
			return itemTitle;
		}

		public void setItemTitle(String itemtitle) {
			this.itemTitle = itemtitle;
		}

		public String getBrandName() {
			return brandName;
		}

		public void setBrandName(String brandname) {
			this.brandName = brandname;
		}

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemname) {
			this.itemName = itemname;
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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
	}
}