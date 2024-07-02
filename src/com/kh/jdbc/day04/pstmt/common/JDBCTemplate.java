package com.kh.jdbc.day04.pstmt.common;

import java.io.*;
import java.sql.*;
import java.util.*;

public class JDBCTemplate {
//	private static String driverName;
//	private static String url;
//	private static String user;
//	private static String password;
	
//	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USER_NAME = "ELLAJDBC";
//	private static final String PASSWORD = "ELLAJDBC";
	private static final String FILE_NAME = "resources/dev.properties";
	private static Properties prop;
	private static Connection conn;
	
//	public JDBCTemplate() {
//		prop = new Properties();
//		try {
//			prop.load(new FileReader("resources/dev.properties"));
//			driverName = prop.getProperty("driverName");
//			url = prop.getProperty("url");
//			user = prop.getProperty("user");
//			password = prop.getProperty("password");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static Connection getConnection() 
						throws SQLException, ClassNotFoundException, IOException {
		prop = new Properties();
		Reader reader = new FileReader(FILE_NAME);
		prop.load(reader);
		String driverName = prop.getProperty("driverName");
		String url 		  = prop.getProperty("url");
		String user 	  = prop.getProperty("user");
		String password   = prop.getProperty("password");
		if(conn == null || conn.isClosed()) {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);			
		}
		return conn;
	}
}


















