
package model.entity;

public class CartBean {
	private String userId;
	private int productCode;
	private String productName;
	private int quantity;
	private int price;
	private int sumPrice;

	public CartBean() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

}
