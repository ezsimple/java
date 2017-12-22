/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package net.ion.oadr2.service.bean;

import java.io.StringWriter;

public class ResponseObject {

	private String venId;
	private String eventId;
	private Object payload;
//	private Ven ven;

	public ResponseObject(String venId,String eventId, Object payload) {
		this.venId = venId;
		this.eventId = eventId;
		this.payload = payload;
	}

	public Object getPayload() {
		return payload;
	}

	public String getVenId() {
		return this.venId;
	}

//	public Ven getVen() {
//		return ven;
//	}
//
//	public void setVen(Ven ven) {
//		this.ven = ven;
//	}


	public String getEventId() {
		return eventId;
	}

	@Override
	public String toString() {
		StringWriter stringWriter = new StringWriter()
			.append("venId:")
			.append(venId)
			.append(",eventId:")
			.append(eventId)
			.append("[")
			.append(payload.getClass().getName())
			.append("]");

		return stringWriter.toString();
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

}
