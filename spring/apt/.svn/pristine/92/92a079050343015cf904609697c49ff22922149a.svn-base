/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package net.ion.oadr2.service.bean;

import net.ion.oadr2.utils.ISOUtils;

import java.util.List;
import java.util.Map;

public class GroupDevice {

	private String participantId;
	private String groupId;	// unique
	private String groupName;	
	private List<String> deviceId;	// _id(ven_devices) 
	private long created = System.currentTimeMillis();
	private String createdBy;
	private List<Map<String, Object>> deviceData;



	public GroupDevice(){}

	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<String> getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(List<String> deviceId) {
		this.deviceId = deviceId;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDisplay() {
		return ISOUtils.format(getCreated());
	}
	
	public List<Map<String, Object>> getDeviceData() {
		return deviceData;
	}

	public void setDeviceData(List<Map<String, Object>> deviceData) {
		this.deviceData = deviceData;
	}
}
