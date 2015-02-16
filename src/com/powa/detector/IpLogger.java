package com.powa.detector;

/**
 * IP Logger Interface for storing each IP data<br>
 * T is data type stored for each ip<br>
 * Example: In this case T is Epochs as Long<br>
 * POWA Interview Test
 * @author RouhAlavi
 * @version 0.0.1
 * @category Domain Model
 * @param <T>
 */
public interface IpLogger<T> {
    /**
     * add ipData to Storage
     * @param ipData
     */
	public void add(T ipData);
	
	/**
	 * Put ipData in index i
	 * @param ipData
	 * @param i index of data
	 */
	public void put(T ipData,int i);
	
	/**
	 * Get no. of elements in storage
	 * @return size of Storage
	 */
	public int getSize();
	
	/**
	 * Get element in index i
	 * @param index
	 * @return ip Data
	 */
	public T get(int index);

}