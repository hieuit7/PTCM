package com.ptcm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class AddDriverGui extends JFrame implements Runnable,ActionListener{
	
	private Config config;
	private JTextField jtfDriverAddress, jtfDriverbirthday, jtfLicense, jtfDriverId, jtfDriverName, jtfDriverPhone;
	private JButton jbtSavec, jbtCancelc;

	public AddDriverGui(Config config) {
		// TODO Auto-generated constructor stub
		
		this.config = config;
		LanguageResource lang = this.config.getLang();
		setTitle(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_CAR_ADDFRAME_TITLE));
		 
		GridLayout grit = new GridLayout(6,6);
		//GridLayout grit1 = new GridLayout(1,1);
		JPanel jp = new JPanel();
		jp.setLayout(grit);
		jp.add(new JLabel("Id: "));
		jp.add(jtfDriverId = new JTextField(20));
		jp.add(new JLabel("Driver Name: "));
		jp.add(jtfDriverName = new JTextField(20));
		jp.add(new JLabel("License: "));
		jp.add(jtfLicense = new JTextField(20));
		jp.add(new JLabel("Driver Address: "));
		jp.add(jtfDriverAddress = new JTextField(20));
		jp.add(new JLabel("Driver Birthday: "));
		jp.add(jtfDriverbirthday = new JTextField(20));
		jp.add(new JLabel("Driver Phone: "));
		jp.add(jtfDriverPhone = new JTextField(20));
		
		
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	/*public static void main(String[] args) {
		LanguageResource lang = new LanguageResource("EN");
		
		Config config;
		try {
			config = new Config(new Database("localhost", "1433", "ptcm", "sa", "1234"),lang);
			new AddDriverGui(config).setVisible(true);;
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
