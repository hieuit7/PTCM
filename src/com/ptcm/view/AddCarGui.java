package com.ptcm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import jdk.internal.org.objectweb.asm.Label;

import com.ptcm.common.Config;
import com.ptcm.common.Database;
import com.ptcm.resource.Language;
import com.ptcm.resource.LanguageResource;

public class AddCarGui extends JFrame implements Runnable{
	
	private Config config;
	private JTextField jtfCarlicense, jtfCartype, jtfId, jtfIdcarowner, jtfLoad;
	private JButton jbtSavec, jbtCancelc;

	public AddCarGui(Config config) {
		// TODO Auto-generated constructor stub
		
		this.config = config;
		LanguageResource lang = this.config.getLang();
		setTitle(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_CAR_ADDFRAME_TITLE));
		 
		GridLayout grit = new GridLayout(5,5);
		JPanel jp = new JPanel();
		jp.setLayout(grit);
		jp.add(new JLabel("Id: "));
		jp.add(jtfId = new JTextField(20));
		jp.add(new JLabel("CarLicense: "));
		jp.add(jtfCarlicense = new JTextField(20));
		jp.add(new JLabel("Car Type"));
		jp.add(jtfCartype = new JTextField(20));
		jp.add(new JLabel("ID Car Owner: "));
		jp.add(jtfIdcarowner = new JTextField(20));
		jp.add(new JLabel("Load: "));
		jp.add(jtfLoad = new JTextField(20));
		
		JPanel jpn = new JPanel();
		jpn.add(jbtSavec = new JButton("Save"));
		jpn.add(jbtCancelc = new JButton("Cancle"));
		JPanel mainPanel = new JPanel();
		BoxLayout blo = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(blo);
		mainPanel.add(jp);
		mainPanel.add(jpn);
		add(mainPanel);
		
		 pack();
		 
	}
	/*
	public static void main(String[] args) {
		LanguageResource lang = new LanguageResource("EN");
		
		Config config;
		try {
			config = new Config(new Database("localhost", "1433", "ptcm", "sa", "builongcuong"),lang);
			new AddCarGui(config).setVisible(true);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	@Override
	public void run() {
		// TODO Auto-generated method stub
		setVisible(true);
	}
	
}
