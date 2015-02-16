package com.powa.detector.impl;

import com.powa.detector.IpLogger;

/**
 * EpochRingBuffer- A Ring Buffer for storing Epochs for each ip<br>
 * Only stores Last ptr(Fixed size) Epochs for each IP
 * @author RouhAlavi
 * @version 0.0.1
 * @category Implementation
 *
 */
public class EpochRingBuffer implements IpLogger<Long> {

    private long[] data;
    int ptr;
    int size;

    /**
     * Generate class with fixed size 
     * @param size
     */
    public EpochRingBuffer(int size) {
    	this.size=size;
    	ptr=0;
        data = new long[size];
    }

    /**
	 * @see com.powa.detector.IpLogger#add(T)
	 */
    @Override
	public synchronized void add(Long ipData) {
        data[ptr] = ipData;
        ptr = (ptr + 1) % size;
    }

    /**
	 * @see com.powa.detector.IpLogger#put(T,int)
	 */
    public synchronized void put(Long ipData, int i) {
    	data[i % size]= ipData;
    };

    /**
	 * @see com.powa.detector.IpLogger#getSize()
	 */
    public int getSize(){
    	return size;
    }
    
    /**
	 * @see com.powa.detector.IpLogger#get(int)
	 */
    @Override
	public Long get(int index) {
        return data[(ptr + index) % size];
    }
    
}