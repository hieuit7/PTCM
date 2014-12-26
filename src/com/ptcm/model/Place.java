package com.ptcm.model;

public class Place {
	private int distance, id;
	private String name;
	public Place(int distance, int id, String name) {
		super();
		this.distance = distance;
		this.id = id;
		this.name = name;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
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
	@Override
	public String toString() {
		return this.name;
	}
	
}
