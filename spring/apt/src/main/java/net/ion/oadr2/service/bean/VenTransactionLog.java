/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package net.ion.oadr2.service.bean;

import java.util.ArrayList;
import java.util.List;

import net.ion.oadr2.specification.Profile.Protocol;
import net.ion.oadr2.specification.Profile.Resource;
import net.ion.oadr2.specification.Profile.Version;


public class VenTransactionLog {

	private String _id;
	private Protocol protocol;
	private Resource resource;
	private String request;
	private String response;
	private Version version;
	private long created = System.currentTimeMillis();

	public VenTransactionLog(){

	}

	public String get_id() {
		return this._id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getRequest() {
		return this.request;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public Protocol getProtocol() {
		return this.protocol;
	}


	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Resource getResource() {
		return this.resource;
	}

	public long getCreated() {
		return this.created;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}


}
