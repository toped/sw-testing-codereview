package org.msstate.cse.changecalculator.utility;

public class Util {
	
	public static boolean isNullOrEmpty(String str){
		if (null == str) 
			{
			return true;
			}
		return str.isEmpty();
	}

}
