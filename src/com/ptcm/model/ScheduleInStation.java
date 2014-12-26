package com.ptcm.model;

import java.sql.Date;

public class ScheduleInStation {
	private int schedule, station;
	private Date satrttime, stoptime;
	public ScheduleInStation(int schedule, int station, Date satrttime,
			Date stoptime) {
		super();
		this.schedule = schedule;
		this.station = station;
		this.satrttime = satrttime;
		this.stoptime = stoptime;
	}
	public int getSchedule() {
		return schedule;
	}
	public void setSchedule(int schedule) {
		this.schedule = schedule;
	}
	public int getStation() {
		return station;
	}
	public void setStation(int station) {
		this.station = station;
	}
	public Date getSatrttime() {
		return satrttime;
	}
	public void setSatrttime(Date satrttime) {
		this.satrttime = satrttime;
	}
	public Date getStoptime() {
		return stoptime;
	}
	public void setStoptime(Date stoptime) {
		this.stoptime = stoptime;
	}
	@Override
	public String toString() {
		return "ScheduleInStation [schedule=" + schedule + ", station="
				+ station + ", satrttime=" + satrttime + ", stoptime="
				+ stoptime + "]";
	}
	
}
