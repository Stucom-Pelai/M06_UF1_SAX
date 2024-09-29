package model;

public class Product {
	private String name;
	private String badge;
	private String color;
	private float price;
	private int stock;
	private int storage;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the badge
	 */
	public String getBadge() {
		return badge;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @return the storage
	 */
	public int getStorage() {
		return storage;
	}

	public Product(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", badge=" + badge + ", color=" + color
				+ ", price=" + price + ", stock=" + stock + ", storage="
				+ storage + "]";
	}

}
