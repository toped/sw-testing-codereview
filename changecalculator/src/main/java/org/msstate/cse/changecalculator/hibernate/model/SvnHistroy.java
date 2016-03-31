package org.msstate.cse.changecalculator. hibernate.model;

public class SvnHistroy {
	private long revNum ;
	private String classPath ;
	private long date_milisecond ;
	
	
	public long getRevNum() {
		return revNum;
	}
	public void setRevNum(long revNum) {
		this.revNum = revNum;
	}
	public String getClassPath() {
		return classPath;
	}
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	public long getDate_milisecond() {
		return date_milisecond;
	}
	public void setDate_milisecond(long date_milisecond) {
		this.date_milisecond = date_milisecond;
	}
	
	

}
