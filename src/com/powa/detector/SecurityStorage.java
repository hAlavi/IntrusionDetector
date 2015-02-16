package com.powa.detector;

/**
 * Storage Interface for Failed Login Storage<br>
 * POWA Interview Test 
 * @author RouhAlavi
 * @version 0.0.1
 * @category Domain Model
 *
 * @param <K> Type for Ip
 * @param <V> Type for Ip Related Data
 */
public interface SecurityStorage<K,V>{

	/**
	 * Add ipData for ip to Storage
	 * @param ip
	 * @param ipData
	 */
	public void addData(K ip, V ipData);
	
	/**
	 * Add ipData for ip(String format) to Storage
	 * @param ip
	 * @param ipData
	 */
	public void addData(String ip,V ipData);
	
	/**
	 * Get Data of ip from Storage
	 * @param ip
	 * @return ip Data
	 */
	public IpLogger<V> getData(K ip);
	
	/**
	 * Get Data of ip(string format) from Storage
	 * @param ip
	 * @return ip Data
	 */
	public IpLogger<V> getData(String ip);
	
	/**
	 * Detects intrusion for specified ip and latest ipData
	 * @param ip
	 * @param ipData
	 * @return true if Intrusion detected
	 */
	public boolean isIntruder(String ip, V ipData);
	
	/**
	 * Sets ipHelper for Translating ip to string and vice versa
	 * @param iph
	 */
	public void setIpHelper(IpHelper<K> iph);

	/**
	 * Gets ipHelper for Translating ip to string and vice versa
	 * @return IP Helper
	 */
	public IpHelper<K> getIpHelper();

}