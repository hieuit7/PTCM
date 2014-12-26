package com.ptcm.model;

public class Car {
	private String carlicense;
	private int cartype, id, idcarowner, load;
	public Car(String carlicense, int cartype, int id, int idcarowner, int load) {
		super();
		this.carlicense = carlicense;
		this.cartype = cartype;
		this.id = id;
		this.idcarowner = idcarowner;
		this.load = load;
	}
	
	
	public Car(int id) {
		
		this.id = id;
	}


	public String getCarlicense() {
		return carlicense;
	}
	public void setCarlicense(String carlicense) {
		this.carlicense = carlicense;
	}
	public int getCartype() {
		return cartype;
	}
	public void setCartype(int cartype) {
		this.cartype = cartype;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdcarowner() {
		return idcarowner;
	}
	public void setIdcarowner(int idcarowner) {
		this.idcarowner = idcarowner;
	}
	public int getLoad() {
		return load;
	}
	public void setLoad(int load) {
		this.load = load;
	}
	@Override
	public String toString() {
		return this.carlicense;
	}
	
}
