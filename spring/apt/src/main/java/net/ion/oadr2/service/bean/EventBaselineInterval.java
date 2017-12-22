/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package net.ion.oadr2.service.bean;

public class EventBaselineInterval {
	private transient String intervalId;

	private String baselineId;
	private int uid = 0; // starts from 0, increments by 1
	private long duration;
	private float value;
	
	public String getIntervalId() {
		return intervalId;
	}
	public void setIntervalId(String intervalId) {
		this.intervalId = intervalId;
	}
	public String getBaselineId() {
		return baselineId;
	}
	public void setBaselineId(String baselineId) {
		this.baselineId = baselineId;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}

}
