package com.powa.detector.impl;

import java.util.Date;
import java.util.Random;

/**
 * Simple Console App
 * @author RouhAlavi
 *
 */
public class ConsoleApp {

	static LogAnalyzerImpl lai= new LogAnalyzerImpl();
	//
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test Started...");
		
		for (int i=0;i<20;i++)
        new Thread(new Runnable(){
            public void run(){
            	Integer sip= (new Random()).nextInt(3);
            	String ip ="10.20.10." + sip.toString();
            	Long epoch= (new Date()).getTime();
            	ip=ip+","+epoch.toString()+","+"FAILURE"+",User.Name";
            	System.out.println("Testing ... "+ip);
            	System.out.println("Result is : "+lai.parseLine(ip));
            	
            }
        }).start();

	}

}
