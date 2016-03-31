package org.msstate.cse.changecalculator.properties;

import java.util.ResourceBundle;

import org.msstate.cse.changecalculator.utility.Util;

public class Properties {
	
	//database
	private String db_url;
	private String db_user;
	private String db_pass;
	private String db_table;
	
	//svn
	private String svn_url;
	private int startRevNum;
	private int endRevNum;
	
	//log
	private String logPath;
	
	
	public Properties(ResourceBundle bundle) {
		this.db_url = bundle.getString("db_url");
		this.db_user = bundle.getString("db_user");
		this.db_pass = bundle.getString("db_pass");
		this.db_table = bundle.getString("db_table");
		this.svn_url = bundle.getString("svn_url");
		this.logPath=bundle.getString("logPath");
		
		//start revision number
		String startRevNum = bundle.getString("startRevNum");
		if(Util.isNullOrEmpty(startRevNum)){
			this.startRevNum = -1;
			System.err.println("Start Revision Number is missing in properties file");
		}else{
			this.startRevNum = Integer.parseInt(startRevNum);
		}
		
		//end revision number
		String endRevNum = bundle.getString("endRevNum");
		if(Util.isNullOrEmpty(endRevNum)){
			this.endRevNum = -1;
			System.err.println("Start Revision Number is missing in properties file");
		}else{
			this.endRevNum = Integer.parseInt(endRevNum);
		}
	}


	public String getDb_url() {
		return db_url;
	}


	public String getDb_user() {
		return db_user;
	}


	public String getDb_pass() {
		return db_pass;
	}


	public String getDb_table() {
		return db_table;
	}


	public String getSvn_url() {
		return svn_url;
	}


	public int getStartRevNum() {
		return startRevNum;
	}


	public int getEndRevNum() {
		return endRevNum;
	}


	public String getLogPath() {
		return logPath;
	}

}
