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

import net.ion.oadr2.service.ADRConstants;
import net.ion.oadr2.utils.ISOUtils;

public class Ven {
	public static enum VEN_STATUS {register, cancel, reregister};

	private String participantId;
	private transient String _id;

	private String venId;
	private String registrationId;

	private String venName;

	// see spec 2.0b 8.4.2.1 section
	private boolean simpleHttpPullModel = true; // true : pull, false : push
	private String profileName = ADRConstants.PROFILE_20A; // 2.0a, 2.0b
	private String transportName = ADRConstants.TRANSPORT_NAME_SIMPLE_HTTP; // XMPP, simpleHTTP
	private String transportAddress; //

	// see spec 2.0b 9.3.4.1 section
	// in case of xmpp, a fully qualified JID must be placed for google talk
	// i.e) mintbass@gmail.com/myven_1

	private transient List<String> programIds;
	private transient List<String> programNames;

	// register - default for a normal ven
	// cancel - cancel request
	// reregister - reregistration request
	private VEN_STATUS status = VEN_STATUS.register;

	private boolean online = true;
	//private String securityType = "RSA"; // ECC, RSA
	private long created = System.currentTimeMillis();
	private String createdBy;
	private long modified = System.currentTimeMillis();
	private String modifiedBy;
	private transient String createdDisplay;


	public Ven(){

	}
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}


	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getVenId() {
		return venId;
	}
	public void setVenId(String venId) {
		this.venId = venId;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public String getVenName() {
		return venName;
	}
	public void setVenName(String venName) {
		this.venName = venName;
	}
	public boolean isSimpleHttpPullModel() {
		return simpleHttpPullModel;
	}
	public void setSimpleHttpPullModel(boolean simpleHttpPullModel) {
		this.simpleHttpPullModel = simpleHttpPullModel;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getTransportName() {
		return transportName;
	}
	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}
	public String getTransportAddress() {
		return transportAddress;
	}
	public void setTransportAddress(String transportAddress) {
		this.transportAddress = transportAddress;
	}
	public VEN_STATUS getStatus() {
		return status;
	}
	public void setStatus(VEN_STATUS status) {
		this.status = status;
	}

	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
//	public String getSecurityType() {
//		return securityType;
//	}
//	public void setSecurityType(String securityType) {
//		this.securityType = securityType;
//	}
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

	public long getModified() {
		return modified;
	}
	public void setModified(long modified) {
		this.modified = modified;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedDisplay() {
		return ISOUtils.format(getModified());
	}
	public List<String> getProgramIds() {
		return programIds;
	}
	public void setProgramIds(List<String> programIds) {
		this.programIds = programIds;
	}
	public List<String> getProgramNames() {
		return programNames;
	}
	public void setProgramNames(List<String> programNames) {
		this.programNames = programNames;
	}

}

