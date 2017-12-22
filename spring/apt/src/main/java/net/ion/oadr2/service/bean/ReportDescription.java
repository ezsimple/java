/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package net.ion.oadr2.service.bean;

public class ReportDescription {
	
	private String _id;
	private String reportId;
	private String marketContext;
	private String readingType;
	private String reportType;
	private String rid;
	private String itemDescription;
	private String itemValue;

	//samplingRate
	private long maxPeriod;
	private long minPeriod;
	private boolean onChange = false;
	
	private String itemBase;
	private String reportDataSource;
	private String reportSubject;
	
	public ReportDescription(){
		
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	
	public String getMarketContext() {
		return marketContext;
	}

	public void setMarketContext(String marketContext) {
		this.marketContext = marketContext;
	}

	public String getReadingType() {
		return readingType;
	}

	public void setReadingType(String readingType) {
		this.readingType = readingType;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public long getMaxPeriod() {
		return maxPeriod;
	}

	public void setMaxPeriod(long maxPeriod) {
		this.maxPeriod = maxPeriod;
	}

	public long getMinPeriod() {
		return minPeriod;
	}

	public void setMinPeriod(long minPeriod) {
		this.minPeriod = minPeriod;
	}

	public boolean isOnChange() {
		return onChange;
	}

	public void setOnChange(boolean onChange) {
		this.onChange = onChange;
	}
	
	public String getItemBase() {
		return itemBase;
	}

	public void setItemBase(String itemBase) {
		this.itemBase = itemBase;
	}

	public String getReportDataSource() {
		return reportDataSource;
	}

	public void setReportDataSource(String reportDataSource) {
		this.reportDataSource = reportDataSource;
	}

	public String getReportSubject() {
		return reportSubject;
	}

	public void setReportSubject(String reportSubject) {
		this.reportSubject = reportSubject;
	}
	
}
