package Models;

public class InventoryStock {

	private String categoryName;
	private String item_Title;
	private int quantity;
	private double price;

	public InventoryStock(String categoryName, String item_Title, int quantity, double price) {

		this.categoryName = categoryName;
		this.item_Title = item_Title;
		this.quantity = quantity;
		this.price = price;
	}

	public InventoryStock(String item_Title, int quantity) {

		this.item_Title = item_Title;
		this.quantity = quantity;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getItem_Title() {
		return item_Title;
	}

	public void setItem_Title(String item_Title) {
		this.item_Title = item_Title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


}
