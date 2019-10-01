package com.tipuana.csa.action.converter;

public class DoubleConverterUtil {
	
	public static String replaceComma(String s) {
    	
		char [] a = s.toCharArray();
		StringBuilder r = new StringBuilder();
		
		for(int i = 0; i < a.length; i++) {
			
			if(a[i] == ',') {
				r.append(".");
			} else {
				r.append(a[i]);
			}
		}
		
		return r.toString();
		
	}
	
	public static String replaceDot(String s) {
    	
		char [] a = s.toCharArray();
		StringBuilder r = new StringBuilder();
		
		for(int i = 0; i < a.length; i++) {
			
			if(a[i] == '.') {
				r.append(",");
			} else {
				r.append(a[i]);
			}
		}
		
		return r.toString();
		
	}

}
