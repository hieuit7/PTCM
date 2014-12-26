package com.ptcm.model;

public class Bus {
	private int dest, distance, id, source;
	private String name;
	public Bus(int dest, int distance, int id, int source, String name) {
		super();
		this.dest = dest;
		this.distance = distance;
		this.id = id;
		this.source = source;
		this.name = name;
	}
	public int getDest() {
		return dest;
	}
	public void setDest(int dest) {
		this.dest = dest;
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
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
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
