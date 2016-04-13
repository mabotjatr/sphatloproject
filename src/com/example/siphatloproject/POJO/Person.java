package com.example.siphatloproject.POJO;

public class Person 
{
	private int personID;
	private String name;
	private String lastname;
	private String email;
	private String phoneNO;
	private String role;
	private String username;
	private String password;
	
	
	
	public Person()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(int personID, String name, String lastname, String email,String phoneNO, String role, String username, String password) {
		super();
		this.personID = personID;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.phoneNO = phoneNO;
		this.role = role;
		this.username = username;
		this.password = password;
	}
	
	public Person(int personID, String name, String lastname, String email,String phoneNO) {
		super();
		this.personID = personID;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.phoneNO = phoneNO;
	}
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNO() {
		return phoneNO;
	}
	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() 
	{
		return "Person [personID=" + personID + ", name=" + name
				+ ", lastname=" + lastname + ", email=" + email + ", phoneNO="
				+ phoneNO + ", role=" + role + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	
	
	
	
	
}
