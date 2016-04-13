package com.example.siphatloproject.POJO;

public class Item
{
	private int id;
	private String ItemDescription;
	private double itemPrice;
	public boolean box;
	
	
	
	public Item(int id, String itemDescription, double itemPrice) 
	{
		super();
		this.id = id;
		ItemDescription = itemDescription;
		this.itemPrice = itemPrice;
	}
	
	
	
	public Item(int id, String itemDescription, double itemPrice, boolean box) {
		super();
		this.id = id;
		ItemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.box = box;
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
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public boolean isBox() {
		return box;
	}
	public void setBox(boolean box) {
		this.box = box;
	}
	
	
	
}
