package com.eums.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection;
	private static DBConnection dBConnection ; 

	private DBConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/training", "postgres", "admin");
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
	}

	public static Connection getDBConnection() {
		try {
			if(dBConnection==null || connection.isClosed())
				dBConnection=new DBConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return connection;
	}
}