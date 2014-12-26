package com.ptcm.model;

public class CarOwner {
	private String address, ownername;
	private int id;
	public CarOwner(String address, String ownername, int id) {
		super();
		this.address = address;
		this.ownername = ownername;
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.ownername;
	}
	
}
