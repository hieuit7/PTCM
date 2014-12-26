package com.ptcm.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

import sun.awt.DefaultMouseInfoPeer;

import com.ptcm.common.Config;
import com.ptcm.common.Database;
import com.ptcm.model.Bus;
import com.ptcm.model.Car;
import com.ptcm.model.Cartype;
import com.ptcm.model.Driver;
import com.ptcm.model.Place;
import com.ptcm.model.Schedule;
import com.ptcm.model.Staff;
import com.ptcm.model.Station;
import com.ptcm.model.StationType;
import com.ptcm.resource.Language;
import com.ptcm.resource.LanguageResource;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;
import com.ptcm.view.AddStationGui;
import com.ptcm.view.listener.TabedPaneMouseClick;

public class MainGui extends JFrame implements Runnable,ActionListener,WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu menuFile,menuEdit,menuHelp;
	private JMenuItem mnitemNew, mnitemOpen, mnitemSave, mnitemopy, mnitemPast, mnitemDelete, mnitemIndex, mnitemFind;
	private JTabbedPane mainTabpane,managermentTabpane;
	private JPanel jpnTabManagermentStation,jpnTabManagermentCar,jpnTabManagermentDriver,jpnTabManagermentOwner,jpnTabSchedule,jpnTabNotify;
	private JTextField searchTextbox, searchTextboxc, searchTextboxd;
	private JButton btnEditStation,btnAddStation,btnDeleteStation,btnNotify,btnGenerateSchedule, btnSearch, btnBus,btnEditCar,btnAddCar, btnDeleteCar, btnSearchc, btnEditDriver,btnAddDriver, btnDeleteDriver, btnSearchD;
	private JCheckBox cbSelectAll, cbSelectAllc, cbSelectAlld;
	private JTable tblStation, tblCar, tblDriver,tblSschedule;
	private JComboBox<String>cbbBus;
	private TableModel mdStationTable,mdCarTable,mdDriverTable,mdOwnerTable,mdScheduleTable,mdNotifyTable;


	private LanguageResource lang;
	private Config config;

	public MainGui(Config config) {

		// TODO Auto-generated constructor stub
		this.config = config;
		setTitle("Passenger Terminal Car manager");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLocation(new Point(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x-GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width/4,
				GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y-GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height/2));
		lang = this.config.getLang();
		createMainTabPane();
		setMinimumSize(new Dimension(600,400));
		setJMenuBar(menuBar = new JMenuBar());
		menuBar.add(menuFile = new JMenu("File"));
		menuFile.add(mnitemNew = new JMenuItem("New"));
		menuFile.add(mnitemOpen = new JMenuItem("Open"));
		menuFile.add(mnitemSave = new JMenuItem("Save"));
		menuBar.add(menuEdit = new JMenu("Edit"));
		menuEdit.add(mnitemopy = new JMenuItem("Copy"));
		menuEdit.add(mnitemPast = new JMenuItem("Past"));
		menuEdit.add(mnitemDelete = new JMenuItem("Delete"));

		menuBar.add(menuHelp = new JMenu("Help"));
		menuHelp.add(mnitemIndex = new JMenuItem("Index"));
		menuHelp.add(mnitemFind = new JMenuItem("Find"));
		pack();


	}


	private void createMainTabPane() {
		JScrollPane jspPane = new JScrollPane(mainTabpane = new JTabbedPane(JTabbedPane.LEFT));
		mainTabpane.addMouseListener(new TabedPaneMouseClick(mainTabpane, this.config));
		mainTabpane.setMinimumSize(new Dimension(600, 400));

		mainTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB), managermentTabpane = new JTabbedPane(JTabbedPane.TOP));

		managermentTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION), jpnTabManagermentStation = new JPanel());

		createTabStation();

		managermentTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_CAR), jpnTabManagermentCar = new JPanel());
		createCar();

		managermentTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_DRIVER), jpnTabManagermentDriver = new JPanel());
		createDriver();

		mainTabpane.addTab(lang.getLanguage(Language.MAIN_SCHEDULE_TAB), jpnTabSchedule = new JPanel());
		createSchedule();

		mainTabpane.addTab(lang.getLanguage(Language.MAIN_NOTIFY_TAB), jpnTabNotify = new JPanel());
		Box b = Box.createVerticalBox();
		JPanel b1 = new JPanel();
		JPanel b2 = new JPanel();

		JPanel jpbtnotify = new JPanel();
		jpbtnotify.add(btnBus = new JButton(lang.getLanguage(Language.MAIN_NOTIFY_TAB_BT_BUS)));
		jpbtnotify.add(cbbBus = new JComboBox<String>());
		b1.add(jpbtnotify);
		/*JScrollPane jscPtable = new JScrollPane(tblStation = new JTable());
		b2.add(jscPtable);*/

		b.add(b1);
		b.add(b2);
		jpnTabNotify.add(b);
		managermentTabpane.addTab("Station", new JPanel());

		add(jspPane);
	}


	private void createTabStation() {
		JPanel p = new JPanel();
		BoxLayout bl = new BoxLayout(p, BoxLayout.Y_AXIS);
		BorderLayout bdl = new BorderLayout();
		p.setLayout(bl);
		JPanel b1 = new JPanel();
		JPanel b2 = new JPanel();
		JPanel jpnbution = new JPanel();
		jpnbution.add(cbSelectAll = new JCheckBox(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION_CB_SELECTALL)));
		jpnbution.add(btnEditStation = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION_BTN_EDIT)));
		btnEditStation.addActionListener(this);
		jpnbution.add(btnAddStation = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION_BTN_ADDSTATION)));
		// TODO Auto-generated method stub
		btnAddStation.addActionListener(this);

		jpnbution.add(btnDeleteStation = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION_BTN_DELETE)));
		btnDeleteStation.addActionListener(this);
		jpnbution.add(searchTextbox = new JTextField(15));
		jpnbution.add(btnSearch = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION_BTN_SEARCH)));
		btnSearch.addActionListener(this);
		b1.add(jpnbution);

		JScrollPane jscPtable = new JScrollPane(tblStation = new JTable());

		ArrayList<ArrayList<String>>data = this.config.getDb().getObject(new Station(0,0,0,""), 1);
		mdStationTable = loadStation(data);



		tblStation.setModel(mdStationTable);
		tblStation.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblStation.setRowSelectionAllowed(true); 

		b2.add(jscPtable);


		//jpnTabManagermentStation.setLayout(new BoxLayout(jpnTabManagermentStation, BoxLayout.Y_AXIS));
		p.add(b1);
		p.add(b2);
		JPanel p1 = new JPanel();
		p1.add(p);
		jpnTabManagermentStation.setLayout(bdl);
		jpnTabManagermentStation.add(b1,BorderLayout.NORTH);
		jpnTabManagermentStation.add(jscPtable);
		//btnAddStation.addActionListener(this);
		//jpnTabManagermentStation.add(b);
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
			System.out.println("place is "+arrayList.get(3));
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
	private void createDriver()
	{
		JPanel p = new JPanel();
		BoxLayout bl = new BoxLayout(p, BoxLayout.Y_AXIS);
		BorderLayout bdl = new BorderLayout();
		p.setLayout(bl);
		JPanel b1 = new JPanel();
		JPanel b2 = new JPanel();
		JPanel jpnbution = new JPanel();
		jpnbution.add(cbSelectAlld = new JCheckBox(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_DRIVER_CB_SELECTALL)));
		jpnbution.add(btnEditDriver = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_DRIVER_BTN_EDIT)));
		jpnbution.add(btnAddDriver = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_DRIVER_BTN_ADDDRIVER)));
		// TODO Auto-generated method stub
		btnAddDriver.addActionListener(this);

		jpnbution.add(btnDeleteDriver = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_DRIVER_BTN_DELETE)));
		jpnbution.add(searchTextboxd = new JTextField(15));
		jpnbution.add(btnSearchD = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_DRIVER_BTN_SEARCH)));
		b1.add(jpnbution);

		JScrollPane jscPtable = new JScrollPane(tblDriver = new JTable());
		ArrayList<ArrayList<String>>data = this.config.getDb().getObject(new Driver(0, "", "", "", new Date(), ""), 1);

		DefaultTableModel tblmd = new DefaultTableModel();
		tblDriver.setModel(tblmd);
		b2.add(jscPtable);


		//jpnTabManagermentStation.setLayout(new BoxLayout(jpnTabManagermentStation, BoxLayout.Y_AXIS));
		p.add(b1);
		p.add(b2);
		JPanel p1 = new JPanel();
		p1.add(p);
		jpnTabManagermentDriver.setLayout(bdl);
		jpnTabManagermentDriver.add(b1,BorderLayout.NORTH);
		jpnTabManagermentDriver.add(jscPtable);
	}

	private void createCar()
	{
		JPanel p = new JPanel();
		BoxLayout bl = new BoxLayout(p, BoxLayout.Y_AXIS);
		BorderLayout bdl = new BorderLayout();
		p.setLayout(bl);
		JPanel b1 = new JPanel();
		JPanel b2 = new JPanel();
		JPanel jpnbution = new JPanel();
		jpnbution.add(cbSelectAllc = new JCheckBox(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_CAR_CB_SELECTALL)));
		jpnbution.add(btnEditCar = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_CAR_BTN_EDIT)));
		jpnbution.add(btnAddCar = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_CAR_BTN_ADDCAR)));
		// TODO Auto-generated method stub
		btnAddCar.addActionListener(this);

		jpnbution.add(btnDeleteCar = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_CAR_BTN_DELETE)));
		jpnbution.add(searchTextboxc = new JTextField(15));
		jpnbution.add(btnSearchc = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_CAR_BTN_SEARCH)));
		b1.add(jpnbution);

		JScrollPane jscPtable = new JScrollPane(tblCar = new JTable());
		ArrayList<ArrayList<String>>data = this.config.getDb().getObject(new Station(0,0,0,""), 1);

		DefaultTableModel tblmd = new DefaultTableModel();
		tblCar.setModel(tblmd);
		b2.add(jscPtable);


		//jpnTabManagermentStation.setLayout(new BoxLayout(jpnTabManagermentStation, BoxLayout.Y_AXIS));
		p.add(b1);
		p.add(b2);
		JPanel p1 = new JPanel();
		p1.add(p);
		jpnTabManagermentCar.setLayout(bdl);
		jpnTabManagermentCar.add(b1,BorderLayout.NORTH);
		jpnTabManagermentCar.add(jscPtable);
	}
	private void createSchedule()
	{
		Box b = Box.createVerticalBox();
		JPanel b1 = new JPanel();
		JPanel b2 = new JPanel();

		JPanel jpBut = new JPanel();
		jpBut.add(btnNotify = new JButton(lang.getLanguage(Language.MAIN_SCHEDULE_TAB_BTN_NOTIFY)));
		jpBut.add(btnGenerateSchedule = new JButton(lang.getLanguage(Language.MAIN_SCHEDULE_TAB_BTN_GENERRATESSCHEDULE)));
		btnGenerateSchedule.addActionListener(this);
		jpBut.add(cbbBus = new JComboBox<String>());
		jpBut.add(searchTextboxc = new JTextField(15));
		jpBut.add(btnSearchc = new JButton(lang.getLanguage(Language.MAIN_SCHEDULE_TAB_BTN_SEARCH)));
		b1.add(jpBut);

		JScrollPane jscPtable = new JScrollPane(tblSschedule = new JTable());
		actionScheduleTab();

		b2.add(jscPtable);

		b.add(b1);
		b.add(b2);
		jpnTabSchedule.add(b);
	}

	private void actionScheduleTab() {

		Database db = this.config.getDb();

		Schedule schedule = new Schedule(0, new Date(2014-1900,9,10), new Date(), 0, 0, 0, 0, "");


		ArrayList<Schedule> s = schedule.getSchedules(new Date(2014-1900,9,10), db);


		ArrayList<ArrayList<String>> obj = db.getObject(schedule, 1);
		Object colName[] = obj.get(0).toArray();

		DefaultTableModel tblmd = new DefaultTableModel();
		tblmd.setColumnIdentifiers(colName);
		for (Schedule schedule2 : s) {
			/**
			 * search car
			 */
			Car car = new Car(schedule2.getIdcar());
			String fi[] = {"id"};
			ArrayList<ArrayList<String>> cars = db.searchObject(car, fi);
			car = new Car(cars.get(0).get(4),Integer.parseInt(cars.get(0).get(3)) , Integer.parseInt(cars.get(0).get(0)), Integer.parseInt(cars.get(0).get(1)), Integer.parseInt(cars.get(0).get(2)));

			/**
			 * Search bus
			 */
			Bus bus = new Bus(0, 0, schedule2.getBus(), 0, "");
			ArrayList<ArrayList<String>> buss = db.searchObject(bus, fi);
			bus = new Bus(Integer.parseInt(buss.get(0).get(1)), Integer.parseInt(buss.get(0).get(3)), Integer.parseInt(buss.get(0).get(0)), Integer.parseInt(buss.get(0).get(2)), buss.get(0).get(4));
			/**
			 * Search driver 
			 */
			Driver driver = new Driver(schedule2.getIddriver(), "", "", "", new Date(), "");
			ArrayList<ArrayList<String>> drivers = db.searchObject(driver, fi);
			driver = new Driver(Integer.parseInt(drivers.get(0).get(0)), drivers.get(0).get(1), drivers.get(0).get(3), drivers.get(0).get(4), db.stringToDate(drivers.get(0).get(2)), drivers.get(0).get(5));
			/**
			 * 
			 * Search Staff
			 */
			Staff staff = new Staff(schedule2.getIdstaff(), "", "", "", new Date());
			ArrayList<ArrayList<String>>staffs = db.searchObject(staff, fi);
			staff = new Staff(Integer.parseInt(staffs.get(0).get(0)), staffs.get(0).get(1), staffs.get(0).get(3), staffs.get(0).get(4), db.stringToDate(staffs.get(0).get(2)));
			/**
			 * Search 
			 */
			////system.out.println("Car is "+car.getCarlicense());
			Object o[] = {schedule2.getId(),schedule2.getName(),car,bus,driver,staff,schedule2.getStart(),schedule2.getFinish()};
			tblmd.addRow(o);


		}
		tblSschedule.setModel(tblmd);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			LookAndFeelInfo look[] = UIManager.getInstalledLookAndFeels();
			for (LookAndFeelInfo lookAndFeelInfo : look) {

				//system.out.println(lookAndFeelInfo.getClassName());
			}
			UIManager.setLookAndFeel(this.config.getLookAndFeel());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnAddStation)){
			//system.out.println("Add station clicked!!");
			AddStationGui addStationgui = new AddStationGui(this.config,tblStation);
			SwingUtilities.invokeLater(addStationgui);


		}else if(o.equals(btnEditStation)){

			int rowSelected = tblStation.getSelectedRow();
			if(rowSelected == -1){
				JOptionPane.showMessageDialog(this, "Please select a Station!!");
			}else{

				ArrayList<ArrayList<String>>pla = this.config.getDb().searchObject(tblStation.getValueAt(rowSelected, 3).toString(), new Place(0, 0, ""));
				ArrayList<ArrayList<String>>typ = this.config.getDb().searchObject(tblStation.getValueAt(rowSelected, 2).toString(), new StationType(0, ""));
				System.out.println(tblStation.getValueAt(rowSelected, 0).toString());
				Station station = new Station(Integer.parseInt(tblStation.getValueAt(rowSelected, 0).toString()), Integer.parseInt(pla.get(1).get(0)), Integer.parseInt(typ.get(1).get(0)), tblStation.getValueAt(rowSelected, 1).toString());
				AddStationGui editStation = new AddStationGui(config, tblStation, 1, station);
				editStation.addWindowListener(this);
				SwingUtilities.invokeLater(editStation);
			}
		}else if(o.equals(btnGenerateSchedule)){

			actionBtnGenerateSchedule();
		}else if(o.equals(btnDeleteStation)){
			int rowSelected[] = tblStation.getSelectedRows();



			int rowdeleted = 0;

			if(rowSelected.length <= 0){
				JOptionPane.showMessageDialog(this, "Please select a Station!");
			}else{
				TableModel md = tblStation.getModel();

				for (int i = 0; i < rowSelected.length; i++) {
					int id = Integer.parseInt(tblStation.getValueAt(rowSelected[i], 0).toString());

					Station station = new Station(id, 0, 0, "");
					try {
						rowdeleted += this.config.getDb().deleteObject(station);
						md = this.loadStation(this.config.getDb().getObject(station, 1));
						tblStation.setModel(md);
						tblStation.updateUI();
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}

				}
				JOptionPane.showMessageDialog(this, rowdeleted+" Station deleted!");

			}

		}else if(o.equals(btnSearch)){
			String text = searchTextbox.getText();
			System.out.println(text);
			if(text.equalsIgnoreCase("")){
				text = "";
			}
			ArrayList<ArrayList<String>> data = this.config.getDb().searchObject(text, new Station(0,0,0,""));
			DefaultTableModel tblmd = loadStation(data);
			//tblmd.fireTableDataChanged();
			tblStation.setModel(tblmd);
			tblmd = (DefaultTableModel)tblStation.getModel();
			tblmd.fireTableDataChanged();

		}
	}



	private void actionBtnGenerateSchedule() {


		/**
		 * Check driver
		 */
		Driver driver  = new Driver(0, "", "", "", new Date(), "");
		ArrayList<ArrayList<String>>drivers = this.config.getDb().getObject(driver, 100);
		ArrayList<Driver> driverA = new ArrayList<>();

		String fi[] = {"driver"};
		for (int i = 1; i< drivers.size(); i++) {
			ArrayList<String> arrayList = drivers.get(i);
			driver = new Driver(Integer.parseInt(arrayList.get(0)), arrayList.get(1), arrayList.get(3), arrayList.get(4), this.config.getDb().stringToDate(arrayList.get(2)), arrayList.get(5));
			Schedule sch = new Schedule(0,new Date(), new Date(), 0	, 0	, driver.getId()	, 0, "");
			if(this.config.getDb().searchObject(sch, fi).size() <= 0){

				driverA.add(driver);
			}
		}


		/**
		 * Check bus
		 */
		Bus bus = new Bus(0, 0, 0, 0, "");
		ArrayList<ArrayList<String>>buss = this.config.getDb().getObject(bus, 100);
		ArrayList<Bus>busA = new ArrayList<>();
		String fib_us[] = {"bus"};
		for (int i = 1; i< buss.size(); i++) {
			ArrayList<String> arrayList = buss.get(i);
			bus = new Bus(Integer.parseInt(arrayList.get(1)), Integer.parseInt(arrayList.get(3)), Integer.parseInt(arrayList.get(0)), Integer.parseInt(arrayList.get(2)), arrayList.get(4));
			busA.add(bus);
			Schedule sch = new Schedule(bus.getId(),new Date(), new Date(), 0	, 0	, 0	, 0, "");
			if(this.config.getDb().searchObject(sch, fib_us).size() <= 0){
			}
		}
		/**
		 * Check Staff
		 */
		Staff staff = new Staff(0, "", "", "", new Date());
		ArrayList<ArrayList<String>> staffs = this.config.getDb().getObject(staff, 100);
		ArrayList<Staff>staffA = new ArrayList<>();
		String fi_staff[] = {"staff"};
		for (int i = 1; i< staffs.size(); i++) {
			ArrayList<String> arrayList = staffs.get(i);
			staff = new Staff(Integer.parseInt(arrayList.get(0)), arrayList.get(1), arrayList.get(3), arrayList.get(4), this.config.getDb().stringToDate(arrayList.get(2)));
			Schedule sch = new Schedule(0,new Date(), new Date(), 0	, 0	, 0	, staff.getId(), "");
			if(this.config.getDb().searchObject(sch, fi_staff).size() <= 0){
				staffA.add(staff);
			}


		}
		/**
		 * check Carr
		 */
		Car car = new Car(0);
		ArrayList<ArrayList<String>> cars = this.config.getDb().getObject(car, 100);
		ArrayList<Car>carA = new ArrayList<>();
		String fi_car[] = {"car"};
		for (int i = 1; i< cars.size(); i++) {
			ArrayList<String> arrayList = cars.get(i);
			car = new Car(arrayList.get(4),Integer.parseInt(arrayList.get(3)) , Integer.parseInt(arrayList.get(0)), Integer.parseInt(arrayList.get(1)), Integer.parseInt(arrayList.get(2)));
			Schedule sch = new Schedule(0,new Date(), new Date(), 0	, car.getId()	, 0	, 0, "");
			if(this.config.getDb().searchObject(sch, fi_car).size() <= 0){
				carA.add(car);
			}
		}
		/**
		 * check staff availale
		 */

		if (busA.size() > 0) {
			for(Bus b : busA){

			}
		}



		/**
		 * check driver available
		 */
		/**
		 * check car available 
		 */




	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("window opened"+e.getSource());
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		//e.getWindow().show();
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("window closed"+e.getSource());
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}



}