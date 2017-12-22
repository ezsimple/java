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

public class Party {
	String partyId;
	String partyName;
	String marketContext;
	List<String> venIds;
	private long created = System.currentTimeMillis();
	String createdBy;
	
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getMarketContext() {
		return marketContext;
	}
	public void setMarketContext(String marketContext) {
		this.marketContext = marketContext;
	}
	public List<String> getVenIds() {
		return venIds;
	}
	public void setVenIds(List<String> venIds) {
		this.venIds = venIds;
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
	
}
