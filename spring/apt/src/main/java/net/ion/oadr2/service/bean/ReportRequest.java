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

import net.ion.oadr2.utils.ISOUtils;

public class ReportRequest {

	private transient String _id;
	private String venId;

	private String reportName;
	private String reportRequestId;

	private long granularity;
	private long reportBackDuration;
	private long startDate;
	private long duration;
	private long created = System.currentTimeMillis();
	
	private List<String> rids = new ArrayList<String>();
	
	private String status;
	public static enum STATUS_TYPE {
		PENDING,
		CANCELLED,
		COMPLETED
	}
	
	
	public ReportRequest(){

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

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportRequestId() {
		return reportRequestId;
	}

	public void setReportRequestId(String reportRequestId) {
		this.reportRequestId = reportRequestId;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public long getGranularity() {
		return granularity;
	}

	public void setGranularity(long granularity) {
		this.granularity = granularity;
	}
	
	public long getReportBackDuration() {
		return reportBackDuration;
	}

	public void setReportBackDuration(long reportBackDuration) {
		this.reportBackDuration = reportBackDuration;
	}
	
	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
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
	
	public void setRids(List<String> rids) {
		this.rids = rids;
	}

	public void setRids(String... rids) {
      if (rids != null) {
            for (String rid : rids) {
                getRids().add(rid);
            }
        }
	}

	public List<String> getRids() {
		return this.rids;
	}
	
}
