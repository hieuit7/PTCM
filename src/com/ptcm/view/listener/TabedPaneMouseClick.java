package com.ptcm.view.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.control.Tab;

import javax.swing.JTabbedPane;

import com.ptcm.common.Config;
import com.ptcm.common.Database;
import com.ptcm.model.Car;
import com.ptcm.model.Schedule;

public class TabedPaneMouseClick implements MouseListener{
	
	private JTabbedPane tabpane;
	private Config config;
	
	
	public TabedPaneMouseClick(JTabbedPane tabpane, Config config) {
		this.config = config;
		this.tabpane = tabpane;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(tabpane)) {
			
			switch (tabpane.getSelectedIndex()) {
			case 0:		
					
				break;
			case 1:
				actionScheduleTab();
				break;
			case 2:
				break;
			default:
				break;
			}
		}
		
	}

	private void actionScheduleTab() {
		
		Database db = this.config.getDb();
			
		Schedule schedule = new Schedule(0, new Date(2014-1900,9,10), new Date(), 0, 0, 0, 0, "");
		
		
		ArrayList<Schedule> s = schedule.getSchedules(new Date(2014-1900,9,10), db);
		
		
		
		for (Schedule schedule2 : s) {
			Car car = new Car(schedule2.getIdcar());
			String fi[] = {"id"};
			ArrayList<ArrayList<String>> cars = db.searchObject(car, fi);
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
