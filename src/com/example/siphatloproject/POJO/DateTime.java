package com.example.siphatloproject.POJO;

import java.util.Date;

public class DateTime 
{
	Date date=new Date();
	String day=String.valueOf(date.getDay());
	String mon=String.valueOf(date.getMonth());
	String year=String.valueOf(date.getYear());
	
	String hour=String.valueOf(date.getHours());
	String min=String.valueOf(date.getMinutes());
	
	public String getDate()
	{
		String date=hour+":"+min+" "+day+"/"+mon+"/"+year;
		
		return date;
	}

}
