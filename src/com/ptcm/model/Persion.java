package com.ptcm.model;

import java.util.Date;

public class Persion {
	
	private int id;
	private String name,phone,address;
	private Date birthday;
	public Persion(int id, String name, String address, String phone,
			Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.birthday = birthday;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBirthday() {
		
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	

}
