package com.powa.detector.impl;

import java.util.concurrent.ConcurrentHashMap;

import com.powa.detector.IpHelper;
import com.powa.detector.IpLogger;
import com.powa.detector.SecurityStorage;

public class LogStorage implements SecurityStorage<Long, Long> {
	ConcurrentHashMap<Long, EpochRingBuffer> storage;
	IpHelper<Long> iph;
	public int size;
	
	public LogStorage(int size){
		this.size = size;
		this.storage= new ConcurrentHashMap<Long, EpochRingBuffer>();
		iph= new IpV4();
	}
	
	/**
	 * @see com.powa.detector.SecurrityStorage#addData(Long, Long)
	 */
	@Override
	public synchronized void addData(Long ip, Long ipData){
		EpochRingBuffer loginEpochs;
		//
		if (storage.containsKey(ip))
		{
			loginEpochs = storage.get(ip);
			loginEpochs.add(ipData);
			
		}
		else
		{
			loginEpochs= new EpochRingBuffer(size);
			loginEpochs.add(ipData);
			storage.put(ip, loginEpochs);
		}
	}
	
	/**
	 * @see com.powa.detector.SecurrityStorage#isIntruder(String, Long)
	 */
	@Override
	public boolean isIntruder(String ip, Long epoch){
		EpochRingBuffer loginEpochs;
		Long ipL=iph.getIp(ip);
		//
		if (storage.containsKey(ipL))
		{
			loginEpochs= storage.get(ipL);
			for (int i=0; i<loginEpochs.getSize();i++)
			{
				Long diffInSeconds= epoch-loginEpochs.get(i);
				Long diff= 300000L;
				if (diffInSeconds>diff)
					return false;
			}
			return true;
					
		}
		else
			return false;
		
	}

	/**
	 * @see com.powa.detector.SecurrityStorage#addData(String, Long)
	 */
	@Override
	public synchronized void addData(String ip, Long ipData) {
		EpochRingBuffer loginEpochs;
		Long ipL= iph.getIp(ip);
		//
		if (storage.containsKey(ipL))
		{
			loginEpochs = storage.get(ipL);
			loginEpochs.add(ipData);
			
		}
		else
		{
			loginEpochs= new EpochRingBuffer(size);
			loginEpochs.add(ipData);
			storage.put(ipL, loginEpochs);
		}
	}

	/**
	 * @see com.powa.detector.SecurrityStorage#getData(Long)
	 */
	@Override
	public IpLogger<Long> getData(Long ip) {
		if (storage.containsKey(ip))
			return storage.get(ip);
		return null;
	}

	/**
	 * @see com.powa.detector.SecurrityStorage#getData(String)
	 */
	@Override
	public IpLogger<Long> getData(String ip) {
		Long ipL= iph.getIp(ip);
		if (storage.containsKey(ipL))
			return storage.get(ipL);
		return null;
	}

	/**
	 * @see com.powa.detector.SecurrityStorage#setIpHelper(IpHelper<Long>)
	 */
	@Override
	public void setIpHelper(IpHelper<Long> iph) {
		this.iph= iph;
	}

	/**
	 * @see com.powa.detector.SecurrityStorage#getIpHelper()
	 */
	@Override
	public IpHelper<Long> getIpHelper() {
		return this.iph;
	}

}
