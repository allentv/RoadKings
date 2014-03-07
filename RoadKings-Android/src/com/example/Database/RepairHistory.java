package com.example.Database;

import java.sql.Date;

public class RepairHistory{
	private String car_model;
	private String parts;
	private String price;
	private String repair_details;
	private String date;
	private String shop_name;
	private String emailId;
	private Integer Id;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public RepairHistory()
	{
		
	}
	public RepairHistory(String car_model, String parts, String price, String repair_details, String date, String shop_name, String emailId)
	{
		this.car_model = car_model;
		this.date = date;
		this.emailId = emailId;
		this.parts = parts;
		this.price = price;
		this.repair_details = repair_details;
		this.shop_name = shop_name;	
		
		System.out.println("in repair history");
		System.out.println("car model"+car_model);
		System.out.println("parts"+parts);
		System.out.println("price"+price);
		System.out.println("repair details"+repair_details);
		System.out.println("date "+date);
		System.out.println("shop name"+shop_name);
		System.out.println("email "+emailId);
	}
	
	public String getCar_model() {
		return car_model;
	}
	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}
	public String getParts() {
		return parts;
	}
	public void setParts(String parts) {
		this.parts = parts;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRepair_details() {
		return repair_details;
	}
	public void setRepair_details(String repair_details) {
		this.repair_details = repair_details;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String id) {
		this.emailId = id;
	}
	
	
	
	
}
