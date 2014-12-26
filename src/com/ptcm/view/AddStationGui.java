package com.ptcm.view;

import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



import java.util.EventObject;

import javax.swing.BoxLayout;
import javax.swing.CellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;




import com.ptcm.common.Config;
import com.ptcm.common.Database;
import com.ptcm.model.Place;
import com.ptcm.model.Station;
import com.ptcm.model.StationType;
import com.ptcm.resource.Language;
import com.ptcm.resource.LanguageResource;

public class AddStationGui extends JFrame implements Runnable,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Config config;
	private JTextField jtfId, jtfName;
	private JComboBox<Place> cbPlace;
	private JComboBox<StationType>cbType;
	private JButton jbtSave, jbtCancel;
	private JTable tblStation;

	private int actionType;
	private Station station;
	public AddStationGui(Config config, JTable tblStation,int actionType,Station station) {
		// TODO Auto-generated constructor stub
		
		this.actionType = actionType;
		this.station = station;
		createGui(config,tblStation);
		
		jtfId.setText(station.getId()+"");
		jtfId.setEditable(false);
		jtfName.setText(station.getName());
		setAlwaysOnTop(true);
		//setEnabled(false);
	}

	public AddStationGui(Config config, JTable tblStation) {
		// TODO Auto-generated constructor stub

		createGui(config, tblStation);
		
	}

	private void createGui(Config config, JTable tblStation) {
		setLocation(new Point(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x-GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width/4,
				GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y));
		this.config = config;
		this.tblStation = tblStation;
		Database db = this.config.getDb();
		LanguageResource lang = this.config.getLang();
		setTitle(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION_ADDFRAME_TITLE));

		GridLayout grit = new GridLayout(2,2);
		JPanel jp = new JPanel();
		JPanel jp2 = new JPanel();
		jp.setLayout(grit);
		jp2.setLayout(grit);
		jp.add(new JLabel("Id: "));
		jp.add(jtfId = new JTextField(20));
		jp.add(new JLabel("Name: "));
		jp.add(jtfName = new JTextField(20));
		jp2.add(new JLabel("Place"));


		jp2.add(cbPlace = new JComboBox<>());
		Place place = new Place(1, 1, "");
		ArrayList<ArrayList<String>> places = db.getObject(place, 100);

		DefaultComboBoxModel<Place> placeMd = new DefaultComboBoxModel<>();
		for (int i = 1; i < places.size(); i++) {
			place = new Place(Integer.parseInt(places.get(i).get(2)), Integer.parseInt(places.get(i).get(0)), places.get(i).get(1));
			placeMd.addElement(place);
			if(actionType ==1)	
			if(place.getId() == station.getType() ){
				placeMd.setSelectedItem(place);
			}
		}
		cbPlace.setModel(placeMd);
		jp2.add(new JLabel("Type: "));
		jp2.add(cbType = new JComboBox<>());
		StationType stp = new StationType(0, "");
		ArrayList<ArrayList<String>>row = db.getObject(stp, 100);

		DefaultComboBoxModel<StationType> cbStationMd = new DefaultComboBoxModel<>(); 
		for (int i = 1; i < row.size(); i++) {
			stp = new StationType(Integer.parseInt(row.get(i).get(0)), row.get(i).get(1));
			cbStationMd.addElement(stp);
			if(actionType ==1)
			if(stp.getId() == station.getType()){
				cbStationMd.setSelectedItem(stp);
			}
		}
		
		cbType.setModel(cbStationMd);
		JPanel jpn = new JPanel();
		jpn.add(jbtSave = new JButton("Save"));
		jbtSave.addActionListener(this);
		jpn.add(jbtCancel = new JButton("Cancle"));
		JPanel mainPanel = new JPanel();
		BoxLayout blo = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(blo);
		mainPanel.add(jp);
		mainPanel.add(jp2);
		mainPanel.add(jpn);
		add(mainPanel);

		pack();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(jbtSave)) {
			int id = 0,place = 0, type = 0;
			String name = jtfName.getText();

			try {
				id = Integer.parseInt(jtfId.getText());

				place = ((Place)cbPlace.getSelectedItem()).getId();
				type = ((StationType)cbType.getSelectedItem()).getId();
				Station station = new Station(id, place, type, name);

				Database db = this.config.getDb();
				String fields[]  = {"id"};
				ArrayList<ArrayList<String>>stations = db.searchObject(station, fields);
				if ((stations.size() > 0 && this.actionType != 1) || name.equalsIgnoreCase("")) {

					if(name.equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(this, "Please input Station Name!");
						jtfName.requestFocus();
					}else{
						JOptionPane.showMessageDialog(this, "id Station is existed!!!");
						jtfId.requestFocus();
					}
				}else{
					if(this.actionType == 0 ){
						db.insertObject(station);
						JOptionPane.showMessageDialog(this, "New Station added!!","Station Message", JOptionPane.INFORMATION_MESSAGE);
					}else{
						db.updateObject(station);
						JOptionPane.showMessageDialog(this, "Station "+station.getName()+" edited!","Station Edit", JOptionPane.INFORMATION_MESSAGE);
					}
						ArrayList<ArrayList<String>>data = this.config.getDb().getObject(new Station(0,0,0,""), 1);
						DefaultTableModel tblmd = loadStation(data);
						tblmd.fireTableDataChanged();
						tblStation.setModel(tblmd);
						

						this.dispose();


					}
				} catch (NumberFormatException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, "Please input number");
				}



			}
		}

		private DefaultTableModel loadStation(ArrayList<ArrayList<String>> data) {

			DefaultTableModel tblmd = new DefaultTableModel();
			int numfield = data.get(0).size();
			String fields[] = new String[numfield];
			for (int i = 0; i < fields.length; i++) {
				fields[i] = new String(data.get(0).get(i));
			}
			tblmd.setColumnIdentifiers(fields);

			for (int i = 1; i< data.size(); i++) {
				ArrayList<String>arrayList = data.get(i);
				/**
				 * get PlaceName;
				 */
				Place place = new Place(0, Integer.parseInt(arrayList.get(3)), "");
				String fis[] = {"id"};
				ArrayList<ArrayList<String>> places = this.config.getDb().searchObject(place, fis);
				place = new Place(Integer.parseInt(places.get(0).get(2)), Integer.parseInt(places.get(0).get(0)), places.get(0).get(1));

				/**
				 * get Station type;
				 */
				StationType stype = new StationType(Integer.parseInt(arrayList.get(2)), "");
				ArrayList<ArrayList<String>> stypes = this.config.getDb().searchObject(stype, fis);
				stype = new StationType(Integer.parseInt(stypes.get(0).get(0)), stypes.get(0).get(1));

				Object stationObj[] = {arrayList.get(0),arrayList.get(1),stype.getName(),place.getName()};
				tblmd.addRow(stationObj);
			}

			return tblmd;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			setVisible(true);
		}

	}
