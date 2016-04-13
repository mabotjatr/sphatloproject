package com.example.siphatloproject.POJO;

public class Notification
{
	private int id;
	private String message;
	private String timeCreated;
	
	
	public Notification(int id, String message, String timeCreated) {
		super();
		this.id = id;
		this.message = message;
		this.timeCreated = timeCreated;
	}


	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getTimeCreated() {
		return timeCreated;
	}


	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}
	
	
}
