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

public class VenProgram {

	private String programId;
	private String venId;
	private String participantId;
	private long created = System.currentTimeMillis();
	private transient String createdDisplay;

	public VenProgram(String programId, String venId, String participantId){
		this.programId = programId;
		this.venId = venId;
		this.participantId = participantId;
	}


	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}
	
	public String getVenId() {
		return venId;
	}

	public void setVenId(String venId) {
		this.venId = venId;
	}
	
	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}


	public String getCreatedDisplay() {
		return ISOUtils.format(getCreated());
	}


}
