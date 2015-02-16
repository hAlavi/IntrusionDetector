package com.powa.detector;

/**
 * Analyzing Log Interface
 * POWA Interview Test
 * @author POWA
 * @version 0.0.1
 * @category Domain Model
 *
 */
public interface LogAnalyzer {
	/**
	 * Returns ip if intrusion detected otherwise null
	 * @param line contains log data
	 * @return string
	 */
	public String parseLine(String line); 
}
