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
import java.util.Collection;
import java.util.List;

import net.ion.oadr2.utils.ISOUtils;

public class Report {

	private transient String reportId;
	private String venId;

	private String reportName;
	private String reportRequestId;
	private String reportSpecifierId;

	private long duration;
	private long startDate;
	private long created = System.currentTimeMillis();
	
	private boolean isRegistered = false;
	private boolean isMetadataReport = false;
	
	private List<String> rids = new ArrayList<String>();
	private String realEnergyRid;
	
	private transient Collection<ReportData> data = new ArrayList<ReportData>();
	private transient Collection<ReportDescription> descriptions = new ArrayList<ReportDescription>();
	
	public Report(){

	}
	
	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
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

	public String getReportSpecifierId() {
		return reportSpecifierId;
	}

	public void setReportSpecifierId(String reportSpecifierId) {
		this.reportSpecifierId = reportSpecifierId;
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
	
	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public Collection<ReportData> getData() {
		return data;
	}

	public void setData(Collection<ReportData> data) {
		this.data = data;
	}

	public Collection<ReportDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Collection<ReportDescription> descriptions) {
		this.descriptions = descriptions;
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
	
	public String getRealEnergyRid() {
		return realEnergyRid;
	}

	public void setRealEnergyRid(String realEnergyRid) {
		this.realEnergyRid = realEnergyRid;
	}
	
	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	
	public boolean isMetadataReport() {
		return isMetadataReport;
	}

	public void setMetadataReport(boolean isMetadataReport) {
		this.isMetadataReport = isMetadataReport;
	}
}
