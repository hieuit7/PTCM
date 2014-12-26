package com.ptcm.model;

import java.util.Date;
import java.util.ArrayList;

import com.ptcm.common.Config;
import com.ptcm.common.Database;

public class Schedule {
	private int bus;
	private Date start, finish;
	private int id, idcar, iddriver, idstaff;
	private String name;
	public Schedule(int bus, Date initialtime, Date finishtime, int id,
			int idcar, int iddriver, int idstaff, String name) {
		super();
		this.bus = bus;
		this.start = initialtime;
		this.finish = finishtime;
		this.id = id;
		this.idcar = idcar;
		this.iddriver = iddriver;
		this.idstaff = idstaff;
		this.name = name;
	}
	public int getBus() {
		return bus;
	}
	public void setBus(int bus) {
		this.bus = bus;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getFinish() {
		return finish;
	}
	public void setFinish(Date finish) {
		this.finish = finish;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdcar() {
		return idcar;
	}
	public void setIdcar(int idcar) {
		this.idcar = idcar;
	}
	public int getIddriver() {
		return iddriver;
	}
	public void setIddriver(int iddriver) {
		this.iddriver = iddriver;
	}
	public int getIdstaff() {
		return idstaff;
	}
	public void setIdstaff(int idstaff) {
		this.idstaff = idstaff;
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
	
	
	public ArrayList<Schedule> generateSchedule(Date date,Database db){
		ArrayList<Schedule> schedules = new ArrayList<>();
		
		
		
		
		
		return schedules;
	}
	
	public ArrayList<Schedule> getSchedules(Date date, Database db) {
		ArrayList<Schedule> result = new ArrayList<>();
		
		
		Schedule schedule = new Schedule(0, date, new Date(), 0, 0, 0, 0, "get");
		String fields[]  = {"start"};
		ArrayList<ArrayList<String>> schedules = db.searchObject(schedule, fields);
		
		for (ArrayList<String> arrayList : schedules) {
			int bus = Integer.parseInt(arrayList.get(3)),id = Integer.parseInt(arrayList.get(0)),idcar = Integer.parseInt(arrayList.get(2)),iddriver = Integer.parseInt(arrayList.get(4)),idstaff = Integer.parseInt(arrayList.get(5));
			String name = arrayList.get(1);
			Date initialtime = db.stringToDate(arrayList.get(6));
			Date finishtime = db.stringToDate(arrayList.get(7));
			Schedule sc = new Schedule(bus, initialtime, finishtime, id, idcar, iddriver, idstaff, name);
			result.add(sc);
		}
		
		
		return result;
		
	}
	
	
}
