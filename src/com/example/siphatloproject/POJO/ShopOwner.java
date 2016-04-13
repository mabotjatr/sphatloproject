package com.example.siphatloproject.POJO;

public class ShopOwner extends Person
{
	String address;
	String shopName;
	String memberName;
	
	public ShopOwner()
	{
		
	}
	
	

	public ShopOwner(int personID, String name, String lastname, String email,
			String phoneNO, String role, String username, String password,String address, String shopName, String memberName) 
	{
		super(personID, name, lastname, email, phoneNO, role, username, password);
		this.address = address;
		this.shopName = shopName;
		this.memberName = memberName;
	}
	




	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getShopName()
	{
		return shopName;
	}
	public void setShopName(String shopName)
	{
		this.shopName = shopName;
	}
	public String getMemberName() 
	{
		return memberName;
	}
	public void setMemberName(String memberName)
	{
		this.memberName = memberName;
	}
	
	

}
