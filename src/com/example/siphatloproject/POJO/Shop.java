package com.example.siphatloproject.POJO;

public class Shop 
{
	private String ownerID;
	private String shopName;
	private String shopAddress;
	
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Shop(String ownerID, String shopName, String shopAddress) {
		super();
		this.ownerID = ownerID;
		this.shopName = shopName;
		this.shopAddress = shopAddress;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	
	

}
