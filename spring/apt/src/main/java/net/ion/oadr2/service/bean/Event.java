/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package net.ion.oadr2.service.bean;

import java.util.List;

public class Event {

	private transient String eventId;

	private String programId;

	private long notificationDate;

	private int priority = 0;
	private long startDate;
	private long endDate;

	// tolerance - duration from active event in milliseconds
	private long tolerance;

	// far - duration from startDate in milliseconds
	private long notification;

	// near - duration from notification in milliseconds
	private long rampUp;

	// recovery - duration from the last active event in milliseconds
	private long recovery;

	// if the event is cancelled, modificationNumber must be increased by 1
	private boolean cancelled = false;
	private boolean responseRequired = true;
	private long modificationNumber = 0L;

	private String comment;

	private long created = System.currentTimeMillis();
	private String createdBy;
	private long modified = System.currentTimeMillis();
	private String modifiedBy;
	private List<String> venIds;
	private List<String> resourceIds;
	private List<String> groupIds;
	private List<String> deviceClassIds;
	private List<String> partyIds;
	private boolean testEvent;

	public Event(){

	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}



	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}



	public long getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(long notificationDate) {
		this.notificationDate = notificationDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public long getTolerance() {
		return tolerance;
	}

	public void setTolerance(long tolerance) {
		this.tolerance = tolerance;
	}

	public long getNotification() {
		return notification;
	}

	public void setNotification(long notification) {
		this.notification = notification;
	}

	public long getRampUp() {
		return rampUp;
	}

	public void setRampUp(long rampUp) {
		this.rampUp = rampUp;
	}

	public long getRecovery() {
		return recovery;
	}

	public void setRecovery(long recovery) {
		this.recovery = recovery;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public boolean isTestEvent() {
		return testEvent;
	}

	public void setTestEvent(boolean testEvent) {
		this.testEvent = testEvent;
	}

	public boolean isResponseRequired() {
		return responseRequired;
	}

	public void setResponseRequired(boolean responseRequired) {
		this.responseRequired = responseRequired;
	}

	public long getModificationNumber() {
		return modificationNumber;
	}

	public void setModificationNumber(long modificationNumber) {
		this.modificationNumber = modificationNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public long getModified() {
		return modified;
	}

	public void setModified(long modified) {
		this.modified = modified;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public List<String> getVenIds() {
		return venIds;
	}

	public void setVenIds(List<String> venIds) {
		this.venIds = venIds;
	}

	public List<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(List<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public List<String> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
	}

	public List<String> getDeviceClassIds() {
		return deviceClassIds;
	}

	public void setDeviceClassIds(List<String> deviceClassIds) {
		this.deviceClassIds = deviceClassIds;
	}
	
	public List<String> getPartyIds() {
		return partyIds;
	}

	public void setPartyIds(List<String> partyIds) {
		this.partyIds = partyIds;
	}

}
