package com.powa.detector.impl;

import com.powa.detector.IpHelper;

/**
 * Implementation of IpHelper interface for IPV4<br>
 * POWA Interview Test
 * @author RouhAlavi
 * @version 0.0.1
 * @category Implementation
 *
 */
public class IpV4 implements IpHelper<Long> {
	/**
	 * Converts 32-bits IPV4 address to string format
	 * @param ip 32-bits ipV4
	 * @return ip in string format
	 */
	public String toString(Long ip){
        // if IP is bigger than 255.255.255.255 or smaller than 0.0.0.0
        if (ip > 4294967295L || ip < 0) {
            throw new IllegalArgumentException("invalid ip");
        }
        StringBuilder ipAddress = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            int shift = i * 8;
            ipAddress.append((ip & (0xff << shift)) >> shift);
            if (i > 0) {
                ipAddress.append(".");
            }
        }
        return ipAddress.toString();
	}
	
	/**
	 * Converts IP in string format to ipv4 32-bits format
	 * @param ipAddr in string format
	 * @return ip 32-bits ipV4
	 */
	public Long getIp(String ipAddr){
        if (ipAddr == null || ipAddr.isEmpty()) {
            throw new IllegalArgumentException("ip address cannot be null or empty");
        }
        String[] octets = ipAddr.split(java.util.regex.Pattern.quote("."));
        if (octets.length != 4) {
            throw new IllegalArgumentException("invalid ip address");
        }
        Long ip = 0L;
        for (int i = 3; i >= 0; i--) {
            Long octet = Long.parseLong(octets[3 - i]);
            if (octet > 255 || octet < 0) {
                throw new IllegalArgumentException("invalid ip address");
            }
            ip |= octet << (i * 8);
        }
        return ip;
    }
	
	/**
	 * Helper function for generating Log Line for further tests
	 * @param ip
	 * @param epoch
	 * @param state
	 * @return generated string
	 */
	public static String makeTestLogStr(String ip, Long epoch, String state){
    	String line =ip;
    	//
    	return (line+","+epoch.toString()+","+state+",User.Name");

	}
		
	
}
