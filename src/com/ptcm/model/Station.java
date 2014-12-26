package com.ptcm.model;

public class Station {
	private int id, place, type;
	private String name;
	public Station(int id, int place, int type, String name) {
		
		this.id = id;
		this.place = place;
		this.type = type;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return this.name;
	}
	
}
