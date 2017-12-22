/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package net.ion.oadr2.service.bean;


public class VenStatus {
	
	private String _id;
	private String venId;
	private String eventId;
	private String requestId;
	private String status;
	private String programId;
	private long created;
	
	
	public VenStatus(){
		
	}

	public String get_id() {
		return this._id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String getVenId() {
		return this.venId;
	}
	
	public void setVenId(String venId) {
		this.venId = venId;
	}
	
	public String getEventId() {
		return this.eventId;
	}
	
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public String getRequestId() {
		return this.requestId;
	}
	
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getProgramId() {
		return this.programId;
	}
	
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	
	public long getCreated() {
		return this.created;
	}
	
	public void setCreated(long created) {
		this.created = created;
	}
	
}
