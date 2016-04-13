package com.example.siphatloproject.POJO;

public class Order
{
	private int OrderId;
	private int userId;
	private String timeCreated;
	private int noOfItems;
	private double unitPrice;
	private String custName;
	private String itemName;
	private int itemId;
	private String status;
	
	
	public Order(int orderId, int userId, String timeCreated, int noOfItems, double unitPrice)
	{
		super();
		OrderId = orderId;
		this.userId = userId;
		this.timeCreated = timeCreated;
		this.noOfItems = noOfItems;
		this.unitPrice = unitPrice;
		
	}
	
	public Order(int orderId, int userId, int itemId,  String timeCreated, int noOfItems, String status, String custName, String itemName, double unitPrice)
	{
		
		super();
		OrderId = orderId;
		this.userId = userId;
		this.timeCreated = timeCreated;
		this.noOfItems = noOfItems;
		this.unitPrice = unitPrice;
		this.custName = custName;
		this.itemName = itemName;
		this.itemId = itemId;
		this.status = status;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [OrderId=" + OrderId + ", userId=" + userId
				+ ", timeCreated=" + timeCreated + ", noOfItems=" + noOfItems
				+ ", unitPrice=" + unitPrice + ", custName=" + custName
				+ ", itemName=" + itemName + ", itemId=" + itemId + ", status="
				+ status + "]";
	}
	
	
	
	
	
}
