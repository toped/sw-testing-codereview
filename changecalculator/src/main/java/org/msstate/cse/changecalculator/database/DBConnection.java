package org.msstate.cse.changecalculator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection = null;
	
	public static Connection createDatabaseConnection(String dbURL, String userName, String password){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(dbURL, userName, password);
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.exit(1);
			} catch (ClassNotFoundException e) {
				System.err.println("Error creating database connection. com.mysql.jdbc.Driver was not found");
				e.printStackTrace();
			}
		return connection;
	}

	public static Connection getConnection() {
		return connection;
	}
	
	

}
