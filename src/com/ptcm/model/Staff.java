package com.ptcm.model;

import java.util.Date;

public class Staff extends Persion {
	public Staff(int id, String name, String address, String phone,
			Date birthday) {
		super(id, name, address, phone, birthday);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}
	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}
	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return super.getAddress();
	}
	@Override
	public void setAddress(String address) {
		// TODO Auto-generated method stub
		super.setAddress(address);
	}
	@Override
	public String getPhone() {
		// TODO Auto-generated method stub
		return super.getPhone();
	}
	@Override
	public void setPhone(String phone) {
		// TODO Auto-generated method stub
		super.setPhone(phone);
	}
	@Override
	public Date getBirthday() {
		// TODO Auto-generated method stub
		return super.getBirthday();
	}
	@Override
	public void setBirthday(Date birthday) {
		// TODO Auto-generated method stub
		super.setBirthday(birthday);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
	}
	
	
}
