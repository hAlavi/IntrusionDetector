package com.powa.detector.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.powa.detector.impl.IpV4;
import com.powa.detector.impl.LogAnalyzerImpl;

/**
 * Functional Test Case 
 * @author RouhAlavi
 * @version 0.0.1
 * @category Test
 */
public class TestCase {
	
	static LogAnalyzerImpl lai= new LogAnalyzerImpl();
   	String line;
   	
   	/**
   	 * Setup test case for functional testing
   	 * @throws Exception
   	 */
	@Before
	public void setUp() throws Exception {
	
    	line = IpV4.makeTestLogStr("10.20.10.1",(new Date()).getTime(),"FAILURE");
    	// add data five times
    	lai.addLog(line);
    	lai.addLog(line);
    	lai.addLog(line);
    	lai.addLog(line);
    	lai.addLog(line);
    	//
    	line = IpV4.makeTestLogStr("10.20.10.2",(new Date()).getTime(),"FAILURE");
    	// add data four times
    	lai.addLog(line);
    	lai.addLog(line);
    	lai.addLog(line);
    	lai.addLog(line);
    	//
    	line = IpV4.makeTestLogStr("10.20.10.3",(new Date()).getTime(),"FAILURE");
    	// add data three times
    	lai.addLog(line);
    	lai.addLog(line);
    	lai.addLog(line);
    	//
    	line = IpV4.makeTestLogStr("10.20.10.4",(new Date()).getTime(),"SUCCESS");
    	// add data three times
    	lai.addLog(line);
    	lai.addLog(line);
    	lai.addLog(line);
    	lai.addLog(line);


	}

	@Test
	public void functionalTest() throws InterruptedException {
		// Has 5 Failed login
		assertEquals(lai.parseLine(IpV4.makeTestLogStr("10.20.10.1",(new Date()).getTime(),"FAILURE")),"10.20.10.1");
		// Has 4 Failed login 
		assertEquals(lai.parseLine(IpV4.makeTestLogStr("10.20.10.2",(new Date()).getTime(),"FAILURE")),"10.20.10.2");
		// Has 3 Failed login 
		assertEquals(lai.parseLine(IpV4.makeTestLogStr("10.20.10.3",(new Date()).getTime(),"FAILURE")),null);
		assertEquals(lai.parseLine(IpV4.makeTestLogStr("10.20.10.3",(new Date()).getTime(),"FAILURE")),"10.20.10.3");
		// All previous logins was success
		assertEquals(lai.parseLine(IpV4.makeTestLogStr("10.20.10.4",(new Date()).getTime(),"FAILURE")),null);
		assertEquals(lai.parseLine(IpV4.makeTestLogStr("10.20.10.4",(new Date()).getTime(),"FAILURE")),null);
		assertEquals(lai.parseLine(IpV4.makeTestLogStr("10.20.10.4",(new Date()).getTime(),"FAILURE")),null);
		assertEquals(lai.parseLine(IpV4.makeTestLogStr("10.20.10.4",(new Date()).getTime(),"FAILURE")),null);
		// After 4 failures now its time to detect intrusion
		assertEquals(lai.parseLine(IpV4.makeTestLogStr("10.20.10.4",(new Date()).getTime(),"FAILURE")),"10.20.10.4");
		// Wait more than 3seconds
		assertEquals(lai.parseLine(IpV4.makeTestLogStr("10.20.10.4",(new Date()).getTime()+3000,"FAILURE")),"10.20.10.4");
		// Wait more than 5 mins (5mins= 5*60*1000=300000milisec)
		// Now It should be OK
		assertEquals(lai.parseLine(IpV4.makeTestLogStr("10.20.10.4",(new Date()).getTime()+300000,"FAILURE")),null);
		
	}

	/**
	 * Concurrent Test - High Load
	 * Tests Full load for 500000 failed login for 65535 possible different ip's
	 */
	@Test
	public void concurrentTest() {
		
		Long startEpoch=(new Date()).getTime();
		int threadCnt= Thread.activeCount();
		
		for (int i=0;i<500000;i++)
	        new Thread(new Runnable(){
	            public void run(){
	            	Integer sip= (new Random()).nextInt(255);
	            	Integer sip2= (new Random()).nextInt(255);
	            	String ip ="10.20." + sip.toString()+ sip2.toString();
	            	Long epoch= (new Date()).getTime();
	            	ip=ip+","+epoch.toString()+","+"FAILURE"+",User.Name";
	            }
	        }).start();

		while(true)
			if (Thread.activeCount()<=threadCnt)
				break;
		// Check test finished in less than 5 min
		assertTrue((new Date().getTime()-startEpoch)<300000L); 
	}


}
