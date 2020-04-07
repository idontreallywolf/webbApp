package com.webapp.tools;

public class StringTool {
	private String string;
	
	public StringTool(String s) {
		this.string = s;
	}
	
	/**
	 * Returns given string with the first letter in Uppercase
	 * 
	 * @return Capitalized string
	 * */
	public static String ucFirst(String s) {
		s = s.toLowerCase();
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
