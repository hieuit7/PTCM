package com.ptcm.resource;

import java.lang.reflect.Field;

public class LanguageResource implements Language{
	
	public String language;
	
	public String EN_MAIN_MANAGERMENT_TAB 						= "Managerment";
	public String EN_MAIN_MANAGERMENT_TAB_STATION 				= "Station";
	public String EN_MAIN_MANAGERMENT_TAB_STATION_CB_SELECTALL 	= "Select all";
	public String EN_MAIN_MANAGERMENT_TAB_STATION_BTN_EDIT 		= "Edit";

	public String EN_MAIN_MANAGERMENT_TAB_STATION_ADDFRAME_TITLE= "New station";
	
	
	
	public String EN_MAIN_MANAGERMENT_TAB_STATION_BTN_ADDSTATION = "New Station";
	public String EN_MAIN_MANAGERMENT_TAB_STATION_BTN_DELETE 	= "Delete";
	public String EN_MAIN_MANAGERMENT_TAB_STATION_BTN_SEARCH		= "Search";
	
	
	public String EN_MAIN_MANAGERMENT_TAB_CAR 					= "Car";
	public String EN_MAIN_MANAGERMENT_TAB_CAR_CB_SELECTALL		= "Select All";
	public String EN_MAIN_MANAGERMENT_TAB_CAR_BTN_EDIT			= "Edit";
	public String EN_MAIN_MANAGERMENT_TAB_CAR_BTN_DELETE		= "Delete";
	public String EN_MAIN_MANAGERMENT_TAB_CAR_BTN_SEARCH		= "Search";
	public String EN_MAIN_MANAGERMENT_TAB_CAR_BTN_ADDCAR		= "New Car";
	public String EN_MAIN_MANAGERMENT_TAB_CAR_ADDFRAME_TITLE	= "New Car";
	
	public String EN_MAIN_MANAGERMENT_TAB_DRIVER 				= "Driver";
	public String EN_MAIN_MANAGERMENT_TAB_DRIVER_CB_SELECTALL	= "Select All";
	public String EN_MAIN_MANAGERMENT_TAB_DRIVER_BTN_EDIT		= "Edit";
	public String EN_MAIN_MANAGERMENT_TAB_DRIVER_BTN_DELETE		= "Delete";
	public String EN_MAIN_MANAGERMENT_TAB_DRIVER_BTN_SEARCH		= "Search";
	public String EN_MAIN_MANAGERMENT_TAB_DRIVER_BTN_ADDDRIVER		= "New Driver";
	public String EN_MAIN_MANAGERMENT_TAB_DRIVER_ADDFRAME_TITLE	= "New Driver";
	
	public String EN_MAIN_MANAGERMENT_TAB_OWNER 					= "Owner";
	
	
	public String EN_MAIN_SCHEDULE_TAB 							= "Schedule";
	public String EN_MAIN_SCHEDULE_TAB_BTN_NOTIFY 					= "Notify";
	public String EN_MAIN_SCHEDULE_TAB_BTN_GENERRATESSCHEDULE 		= "Generate Schedule";
	public String EN_MAIN_SCHEDULE_TAB_CB_BUS 						= "Bus";
	public String EN_MAIN_SCHEDULE_TAB_BTN_SEARCH					= "Search";
	
	public String EN_MAIN_NOTIFY_TAB 							= "Notify";
	public String EN_MAIN_NOTIFY_TAB_BT_BUS 					= "Bus";
	
	public String EN_MAIN_BTN_SEARCH							= "Search";
	

	
	public String VI_MAIN_MANAGERMENT_TAB 						= "Quản lý";
	public String VI_MAIN_MANAGERMENT_TAB_STATION 				= "Trạm xe";
	public String VI_MAIN_MANAGERMENT_TAB_STATION_CB_SELECTALL 	= "Chọn tất cả";
	public String VI_MAIN_MANAGERMENT_TAB_STATION_BTN_EDIT 		= "Sửa";
	
	public String VI_MAIN_MANAGERMENT_TAB_STATION_BTN_ADDSTATION = "Thêm trạm";
	public String VI_MAIN_MANAGERMENT_TAB_STATION_BTN_DELETE 	= "Xóa";
	
	public String VI_MAIN_MANAGERMENT_TAB_CAR 					= "Xe";
	public String VI_MAIN_MANAGERMENT_TAB_DRIVER 				= "Tài xế";
	public String VI_MAIN_MANAGERMENT_TAB_OWNER 					= "Chủ xe";
	
	
	public String VI_MAIN_SCHEDULE_TAB 							= "Lịch trình";
	public String VI_MAIN_NOTIFY_TAB 							= "Thông báo";
	
	
	
	
	
	
	
	
	
	
	
	
	
	public LanguageResource(String language) {
		// TODO Auto-generated constructor stub
		this.language = language;
	}
	public String getLanguage(String text){
		String language = "";
		try{
			language = this.getValue(this.language+"_"+text);
		}catch(Exception e){
			e.printStackTrace();
		}
		return language;
	}
	
	private String getValue(String text){
		
		LanguageResource obj = new LanguageResource(language);
		Class<?>cls = obj.getClass();		
		String value = "";
		try {
			Field field = cls.getDeclaredField(text);
			value = (String)field.get(obj);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return value;
	}
	
	
	
}