package com.powa.detector.impl;

import com.powa.detector.*;

/**
 * Implementation of LogAnalyzer Interface
 * @author RouhAlavi
 * @version 0.0.1
 * @category Implementation
 *
 */
public class LogAnalyzerImpl implements LogAnalyzer {
	LogStorage ipStorage;
	
	/**
	 * Creates class with Max 5 login time storage 
	 */
	public LogAnalyzerImpl(){
		ipStorage= new LogStorage(5);//only stores 5 last failed login epochs
	}
	
	/**
	 * @see com.powa.detector.LogAnalyzer#parseLine(String)
	 */
	@Override
	public synchronized String parseLine(String line) {
		
		String[] parts = line.split(",");
		if (parts[2].trim().toUpperCase().equals("SUCCESS"))
			return null;
		
		ipStorage.addData(parts[0], (Long)Long.parseLong(parts[1]));
		if (ipStorage.isIntruder(parts[0], Long.parseLong(parts[1])))
			return parts[0];
		return null;
	}
	
	/**
	 * Just add line data to log storage without any intrusion test
	 * @param line
	 */
	public synchronized void addLog(String line){
		String[] parts = line.split(",");
		if (parts[2].trim().toUpperCase().equals("SUCCESS"))
			return;
		
		ipStorage.addData(parts[0], (Long)Long.parseLong(parts[1]));
	}
	
}
