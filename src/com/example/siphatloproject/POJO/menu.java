package com.example.siphatloproject.POJO;

public class menu
{
	private int id;
	private String ItemDescription;
	private float itemPrice;
	
	public menu(int id, String itemDescription, float itemPrice) {
		super();
		this.id = id;
		ItemDescription = itemDescription;
		this.itemPrice = itemPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemDescription() {
		return ItemDescription;
	}

	public void setItemDescription(String itemDescription) {
		ItemDescription = itemDescription;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public String toString() {
		return "menu [id=" + id + ", ItemDescription=" + ItemDescription
				+ ", itemPrice=" + itemPrice + "]";
	}
	
	
	
	
	
}
