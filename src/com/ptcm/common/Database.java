package com.ptcm.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;




public class Database {
	public final String PREFIX = "PTCM_";
	private Connection connection;

	public Database(String host,String port,String database,String username, String password) throws Exception{
		// TODO Auto-generated constructor stub
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		this.connection = DriverManager.getConnection("jdbc:sqlserver://"+host+":"+port+";database="+database,username,password);
	}

	private Class<?> getObjectClass(Object obj){
		return obj.getClass();
	}

	private String getTableName(String clsName){
		////system.out.println("Class name is: "+clsName);
		StringTokenizer string = new StringTokenizer(clsName," ");
		String token = "";
		////system.out.println("Number of token"+string.countTokens());
		while(string.hasMoreTokens()){
			token = string.nextToken();
			/////system.out.println("Token is: "+ token);
		}
		string = new StringTokenizer(token,".");
		while(string.hasMoreTokens()){
			token = string.nextToken();
			/////system.out.println("Token is: "+ token);
		}
		return token;

	}

	/**
	 * Insert data into table instance of Object class name
	 * @param obj Object to insert database
	 * @return numrows effected!
	 */
	public int insertObject(Object obj) {
		String table = this.getTableName(this.getObjectClass(obj).toString());
		String fieldName[] = this.getField(obj);

		String sql = "INSERT INTO "+PREFIX+table+"(";

		for (int i = 0; i < fieldName.length; i++) {
			sql += fieldName[i];
			if(i != fieldName.length -1)
				sql += ",";
		}
		sql += ") values(";
		int result = -1;
		try {
			Object value[] = this.getValue(obj);
			for (int i = 0; i < value.length; i++) {
				if(value[i] instanceof Date){
					Date date = (Date)value[i];

					String values = this.dateFormat(date);
					sql += "'"+values+"'";
				}else{
					if(!validUtf8(value[i].toString())){
						sql += "N'"+value[i]+"'";
					}else{
						sql += "'"+value[i]+"'";
					}
				}

				if(i != value.length -1)
					sql += ",";
			}



			sql += ")";
			//system.out.println(sql);
			Statement stt = this.connection.createStatement();
			result = stt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public int deleteObject(Object obj) throws Exception{
		String table = this.getTableName(this.getObjectClass(obj).toString());
		String fieldName[] = this.getField(obj);
		Object values[] = this.getValue(obj);
		//delete from primary key
		String coldelete[] = this.getPrimaryKey(table);
		System.out.println("col delete"+coldelete.length);
		int index[] = new int[coldelete.length];
		for (int i = 0; i < coldelete.length; i++) {
			for (int j = 0; j < fieldName.length; j++) {
				if(coldelete[i].equalsIgnoreCase(fieldName[j])){
					index[i] = j;
				}
			}
		}
		
		
		
		String sql = "DELETE "+PREFIX+table+" WHERE ";
		for (int i = 0; i < index.length; i++) {
			sql += coldelete[i]+"='"+values[index[i]]+"'";
		}
		Statement stt = this.connection.createStatement();
		
		System.out.println(sql);
		
		return stt.executeUpdate(sql);
	}
	
	/**
	 * Get data from database with Object give
	 * @param obj Object give choose table
	 * @param page number of page to show
	 * @return ArrayList<ArrayList<String>>
	 */
	public ArrayList<ArrayList<String>> getObject(Object obj,int page){

		String tableName = this.getTableName(this.getObjectClass(obj).toString());
		ArrayList<ArrayList<String>> data = new ArrayList<>();
		try {
			String sql = "SELECT TOP "+(page*30)+"* FROM "+PREFIX+tableName;
			//system.out.println(sql);
			Statement stt = this.connection.createStatement();
			stt.execute(sql);
			ResultSet result = stt.getResultSet();
			ResultSetMetaData metadata = result.getMetaData();
			ArrayList<String> row = new ArrayList<>();
			int colsize = metadata.getColumnCount();
			for (int i = 0; i < colsize; i++) {
				row.add(metadata.getColumnName(i+1));
			}
			data.add(row);

			while(result.next()){
				ArrayList<String> rows = new ArrayList<>();
				for (int i = 0; i < colsize; i++) {
					rows.add(result.getString(i+1));
				}
				data.add(rows);
			}

			return data;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}

	/**
	 * Update Object give
	 * @param obj 
	 * @return
	 */
	public int updateObject(Object obj){
		String tableName = this.getTableName(this.getObjectClass(obj).toString());

		String fields[] = this.getField(obj);

		try {
			Object values[] = this.getValue(obj);
			String sql = "UPDATE "+PREFIX+tableName+ " SET ";

			for (int i = 1; i < values.length; i++) {
				sql += fields[i]+"=";
				if(values[i] instanceof Date ){
					sql += "'"+this.dateFormat((Date)values[i])+"'";
				}else{
					if(!this.validUtf8(values[i].toString())){
						sql += "N'"+values[i]+"'";
					}else{
						sql += "'"+values[i]+"'";
					}
				}
				if(i != values.length -1){
					sql += ",";
				}
			}
			sql += " WHERE "+fields[0]+"='"+values[0]+"'";
			Statement stt = this.connection.createStatement();
			//system.out.println(sql);
			int result = stt.executeUpdate(sql);

			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}






		return 0;
	}
	/**
	 * search anything in Object give with contains String text
	 * @param text text for search
	 * @param obj Object to search
	 * @return ArrayList<ArrayList<String>>
	 */

	public ArrayList<ArrayList<String>> searchObject(String text,Object obj){

		String tableName = this.getTableName(this.getObjectClass(obj).toString());

		String sql = "SELECT * FROM "+PREFIX+tableName+" WHERE ";
		String fields[] = this.getField(obj);

		for (int i = 0; i < fields.length; i++) {
			if(!this.validUtf8(text)){
				sql += fields[i] + " like N'%"+text+"%'";
			}else{
				sql += fields[i] + " like '%"+text+"%'";
			}
			if(i != fields.length -1 )
				sql += " OR ";
		}

		ArrayList<ArrayList<String>>data = new ArrayList<>();
		try {
			Statement stt = this.connection.createStatement();
			System.out.println(sql);
			stt.executeQuery(sql);
			ResultSet result = stt.getResultSet();
			ResultSetMetaData metadata = result.getMetaData();

			ArrayList<String> header = new ArrayList<>();
			for (int i = 0; i < metadata.getColumnCount(); i++) {
				header.add(metadata.getColumnName(i+1));
			}
			data.add(header);
			while(result.next()){
				ArrayList<String>row = new ArrayList<>();
				for (int i = 0; i < metadata.getColumnCount(); i++) {
					row.add(result.getString(i+1));
				}
				data.add(row);
			}

			return data;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	/**
	 * 
	 * @param obj
	 * @param fields
	 * @return
	 */

	public ArrayList<ArrayList<String>> searchObject(Object obj,String fields[]){

		ArrayList<ArrayList<String>>resultData = new ArrayList<>();
		try {
			Object data[];
			String field[];
			int index[] = new int[fields.length];
			field = this.getField(obj);
			int numfield = 0;
			for (int i = 0; i < fields.length; i++) {
				for (int j = 0; j < field.length; j++) {
					if(fields[i].equalsIgnoreCase(field[j])){
						index[numfield] = j;
						numfield++;
						break;
					}
				}
			}
			//system.out.println(numfield +" == "+fields.length);
			if(numfield == fields.length){
				data = this.getValue(obj);
				String tableName = this.getTableName(this.getObjectClass(obj).toString());

				String sql = "SELECT * FROM "+PREFIX+tableName+" WHERE ";
				for (int i = 0; i < index.length; i++) {
					if(data[index[i]] instanceof Date){
						//system.out.println(this.dateFormat((Date)data[index[i]], "yyyy-MM-dd"));
						sql +=" CONVERT(date," +fields[i]+",126)='"+this.dateFormat((Date)data[index[i]], "yyyy-MM-dd")+"'";
					}else{
						sql += fields[i]+"='"+data[index[i]]+"'";
					}
					if(i != index.length -1)
						sql += " AND ";

				}
				Statement stt = this.connection.createStatement();
				//system.out.println(sql);
				ResultSet result = stt.executeQuery(sql);
				ResultSetMetaData meta = result.getMetaData();
				while(result.next()){
					ArrayList<String>row = new ArrayList<>();
					for (int i = 0; i < meta.getColumnCount(); i++) {
						row.add(result.getString(i+1));
					}
					resultData.add(row);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return resultData;
	}

	public String[] getField(Object obj){
		Class<?> cls = obj.getClass();
		Field fields[] = cls.getDeclaredFields();
		Field supperFields[] = cls.getSuperclass().getDeclaredFields();


		int fieldLength = fields.length;
		int fieldSuperLength = supperFields.length;
		String data[] = new String[fieldLength+fieldSuperLength];


		for (int i = 0; i < fieldSuperLength; i++) {
			data[i] = supperFields[i].getName();
		}
		for (int i = 0; i < fieldLength; i++) {
			data[i+fieldSuperLength] = fields[i].getName();
		}
		return data;

	}

	private Object[] getValue(Object obj) throws Exception{
		String field[] = this.getField(obj);
		Class<?> cls = obj.getClass();
		Object data[] = new Object[field.length];


		for (int i = 0; i < field.length; i++) {
			//system.out.println("Field "+field[i]);
			Class<?>clss[] = new Class[0]; 
			String pre = ""+field[i].charAt(0);
			pre = pre.toUpperCase();
			String methodName = pre+field[i].substring(1);
			Method mt = cls.getDeclaredMethod("get"+methodName,clss);
			/////system.out.println("Invoke method "+methodName);

			data[i] =  mt.invoke(obj,new Object[]{});
			////system.out.println(mt.invoke(obj,new Object[]{}));
		}
		return data;

	}

	/*public static void main(String[] args) throws Exception {


		Database db = new Database("localhost", "1433", "PTCM", "sa", "1234");
		Driver dr = new Driver(1, "haha", "sdasdas", "1", new Date(), "");
		db.insertObject(dr);
		db.updateObject(dr);
		ArrayList<ArrayList<String>> data = db.getObject(dr, 1);
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				//system.out.println(data.get(i).get(j));
			}
		}

	}
	 */	

	public String dateFormat(Date date){

		SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dfm.format(date);
	}
	public String dateFormat(Date date, String format){
		SimpleDateFormat dfm = new SimpleDateFormat(format);
		return dfm.format(date);
	}
	
	public Date stringToDate(String date) {
		SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(date == null){
				return dfm.parse("0000-00-00 00:00:00");
				
			}
			return dfm.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			dfm.applyPattern("yyyy-MM-dd");
			try {
				return dfm.parse(date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
		
	}
	
	public boolean validUtf8(String input){
		CharsetDecoder cs = Charset.forName("UTF-8").newDecoder();
		byte bytes[] = input.getBytes();
	    try {
	        cs.decode(ByteBuffer.wrap(bytes));
	        return true;
	    }
	    catch(CharacterCodingException e){
	        return false;
	    }       
		
	}
	
	private String[] getPrimaryKey(String tableName) throws Exception{
		String sql = "SELECT column_name FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE OBJECTPROPERTY(OBJECT_ID(constraint_name), 'IsPrimaryKey') = 1 AND table_name = '"+PREFIX+tableName+"'";
		Statement stt = this.connection.createStatement();
		stt.execute(sql);
		ResultSet result= stt.getResultSet();
		ArrayList<String>col = new ArrayList<>();
		while(result.next()){
			col.add(result.getString(1));
		}
		String cols[] = new String[col.size()];
		System.out.println("sssss "+col.size());
		for (int i = 0; i < cols.length; i++) {
			cols[i] = new String(col.get(i));
		}
		return cols;
		
		
	}


}