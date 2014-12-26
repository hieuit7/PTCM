package com.ptcm.controller;

import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import com.ptcm.common.Config;
import com.ptcm.common.Database;
import com.ptcm.resource.LanguageResource;
import com.ptcm.view.LoginGui;
import com.ptcm.view.MainGui;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.javafx.scene.accessibility.Attribute;

public class MainThread {
	public static void main(String[] args) {
		LanguageResource lang  = new LanguageResource("EN");
		Database db;
		try {
			db = new Database("localhost", "1433", "ptcm", "sa", "1234");
			Config config = new Config(db,lang);
			config.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			LoginGui loginGui = new LoginGui(config);
			setUIFont();
			SwingUtilities.invokeAndWait(loginGui);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		



	}
	public static void setUIFont (javax.swing.plaf.FontUIResource f){
		java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
		
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();

			Object value = UIManager.get (key);
			if (value != null && value instanceof javax.swing.plaf.FontUIResource){
				 
				
				//System.out.println(((FontUIResource)value).getSize());
				UIManager.put (key, f);
			}
		}
	}
	public static void setUIFont (){
		java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
		
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();

			Object value = UIManager.get (key);
			if (value != null && value instanceof javax.swing.plaf.FontUIResource){
				 
				FontUIResource uir = (FontUIResource)value;
				Font font = new Font(uir.getFontName(), uir.getStyle(), uir.getSize()+2);
				//System.out.println(uir.getSize());
				FontUIResource n = new FontUIResource(font);
				UIManager.put (key, n);
			}
		}
	}
}
