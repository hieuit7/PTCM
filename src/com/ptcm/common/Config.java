package com.ptcm.common;

import com.ptcm.model.User;
import com.ptcm.resource.LanguageResource;

public class Config {
	private Database db;
	private LanguageResource lang;
	private User user;
	private String lookAndFeel;
	
	
	public Config(Database db, LanguageResource lang) {
		this.db = db;
		this.lang = lang;
	}
	
	public Config() {
		// TODO Auto-generated constructor stub
		try {
			this.db = new Database("localhost",	"1433","ptcm","sa","1234");
			this.lang = new LanguageResource("VI");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public Database getDb() {
		return db;
	}
	public void setDb(Database db) {
		this.db = db;
	}
	public LanguageResource getLang() {
		return lang;
	}
	public void setLang(LanguageResource lang) {
		this.lang = lang;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLookAndFeel() {
		return lookAndFeel;
	}

	public void setLookAndFeel(String lookAndFeel) {
		this.lookAndFeel = lookAndFeel;
	}
	
	
	

}
