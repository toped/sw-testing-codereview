package org.msstate.cse.changecalculator.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.msstate.cse.changecalculator.changeproneness.ChangePronenessManager;
import org.msstate.cse.changecalculator.database.DBConnection;
import org.msstate.cse.changecalculator.database.DbUtil;
import org.msstate.cse.changecalculator.log.TeePrintStream;
import org.msstate.cse.changecalculator.properties.Properties;
import org.msstate.cse.changecalculator.utility.Util;

public class Main {

	public static void main(String[] args) {
		Main mainClass = new Main();
		try{
			// Introduction
			System.out.println("Welcome to the Change Calculator!\n\n");
			// Request properties file
			Properties prop = mainClass.getProperties();
			//console log output to file
			mainClass.consoleOutputToFile(prop);
			//log start time
			mainClass.logStartTime();
			//initialization
			mainClass.initialization(prop);
			
			//get change proneness
			ChangePronenessManager CPManager = new ChangePronenessManager(prop);
			CPManager.calculateChangeProneness();
			
		}catch (Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}finally {
			//closing
			mainClass.close();
			mainClass.logEndTime();
			System.out.println("Program Ended");
		}
	}
	
	private Properties getProperties(){
		// Request properties file
		System.out.println("What is the name of your properties file? (don't "
				+ "include '.properties')");
		Scanner input = new Scanner(System.in);
		String propFileName = input.next();
		input.close();
		ResourceBundle propFileBundle = ResourceBundle.getBundle("config/"
				+ propFileName);
		return new Properties(propFileBundle);
	}
	
	private void consoleOutputToFile(Properties prop){
		try {
			String logPath = prop.getLogPath();
			if(Util.isNullOrEmpty(logPath)){
				System.err.println("Log path is invalid");
			}
				String fileName = new Date().getTime()+".txt";
				FileOutputStream fileOS = new FileOutputStream(logPath+"//"+fileName,true);
				TeePrintStream tee = new TeePrintStream(fileOS, System.out);
			    System.setOut(tee);
			    System.out.println(fileName +" file created at "+logPath);
			} catch (FileNotFoundException e) {
			System.err.println("Error configuring console log file");
			e.printStackTrace();
		}
	}
	
	private void logStartTime(){
		//printing current time
		Date startTime = Calendar.getInstance().getTime();
		System.out.println("\nStart Time: "+ startTime+"\n");
	}
	
	private void logEndTime(){
		//printing current time
		Date endTime = Calendar.getInstance().getTime();
		System.out.println("\nEnd Time: "+ endTime+"\n");
	}
	
	private void initialization(Properties prop){
		//initialize database connection
		DBConnection.createDatabaseConnection(prop.getDb_url(), prop.getDb_user(),
				prop.getDb_pass());
		DbUtil.setTableName(prop.getDb_table());
	}
	
	private void close(){
		//close db connection
		Connection conn = DBConnection.getConnection();
		try {
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
