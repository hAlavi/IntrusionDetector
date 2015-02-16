package com.powa.detector;

/**
 * IP Helper Class<br>
 * Interface for Translating different IP styles(ipv4,ipv6)/ further implementations<br>
 * POWA Interview Test<br>
 * 
 * @author RouhAlavi
 * @version 0.0.1
 * @category Domain Model
 * @param <T> Type for storing ip
 */
public interface IpHelper<T> {
	/**
	 * Convert ip type T to String
	 * @param ip
	 * @return String presentation of ip
	 */
	public String toString(T ip);
	/**
	 * Convert String presentation of ip to ip Type T
	 * @param ipAddr
	 * @return Ip Type T
	 */
	public T getIp(String ipAddr);
}
