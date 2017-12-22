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

public class ReportData {
	
	private String _id;
	private String venId;
	
	private List<String> rids = new ArrayList<String>();
	
	private String reportRequestId;
	private String reportName;
	
	private int uid;
	private long startDate;
	private long endDate;
	private long duration;
	private float value;
	private long confidence;
	private float accuracy;
	private String dataQuality;
	private boolean optIn;
	
	private float cp_min;
	private float cp_max;
	private float cp_current;
	private float cp_normal;
	private float lo_min;
	private float lo_max;
	private float lo_current;
	private float lo_normal;
	private float po_min;
	private float po_max;
	private float po_current;
	private float po_normal;
	private float sp_min;
	private float sp_max;
	private float sp_current;
	private float sp_normal;
	
	
	public ReportData(){
		
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
	
	public String getReportRequestId() {
		return reportRequestId;
	}

	public void setReportRequestId(String reportRequestId) {
		this.reportRequestId = reportRequestId;
	}
	
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
	public long getConfidence() {
		return confidence;
	}

	public void setConfidence(long confidence) {
		this.confidence = confidence;
	}
	
	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
	
	public String getDataQuality() {
		return dataQuality;
	}

	public void setDataQuality(String dataQuality) {
		this.dataQuality = dataQuality;
	}
	
	public boolean getOptIn() {
		return optIn;
	}

	public void setOptIn(boolean optIn) {
		this.optIn = optIn;
	}
	
	public float getCp_min() {
		return cp_min;
	}

	public void setCp_min(float cp_min) {
		this.cp_min = cp_min;
	}
	
	public float getCp_max() {
		return cp_max;
	}

	public void setCp_max(float cp_max) {
		this.cp_max = cp_max;
	}
	
	public float getCp_current() {
		return cp_current;
	}

	public void setCp_current(float cp_current) {
		this.cp_current = cp_current;
	}
	
	public float getCp_normal() {
		return cp_normal;
	}

	public void setCp_normal(float cp_normal) {
		this.cp_normal = cp_normal;
	}
	
	public float getLo_min() {
		return lo_min;
	}

	public void setLo_min(float lo_min) {
		this.lo_min = lo_min;
	}
	
	public float getLo_max() {
		return lo_max;
	}

	public void setLo_max(float lo_max) {
		this.lo_max = lo_max;
	}
	
	public float getLo_current() {
		return lo_current;
	}

	public void setLo_current(float lo_current) {
		this.lo_current = lo_current;
	}
	
	public float getLo_normal() {
		return lo_normal;
	}

	public void setLo_normal(float lo_normal) {
		this.lo_normal = lo_normal;
	}
	
	public float getPo_min() {
		return po_min;
	}

	public void setPo_min(float po_min) {
		this.po_min = po_min;
	}
	
	public float getPo_max() {
		return po_max;
	}

	public void setPo_max(float po_max) {
		this.po_max = po_max;
	}
	
	public float getPo_current() {
		return po_current;
	}

	public void setPo_current(float po_current) {
		this.po_current = po_current;
	}
	
	public float getPo_normal() {
		return po_normal;
	}

	public void setPo_normal(float po_normal) {
		this.po_normal = po_normal;
	}
	
	public float getSp_min() {
		return sp_min;
	}

	public void setSp_min(float sp_min) {
		this.sp_min = sp_min;
	}
	
	public float getSp_max() {
		return sp_max;
	}

	public void setSp_max(float sp_max) {
		this.sp_max = sp_max;
	}
	
	public float getSp_current() {
		return sp_current;
	}

	public void setSp_current(float sp_current) {
		this.sp_current = sp_current;
	}
	
	public float getSp_normal() {
		return sp_normal;
	}

	public void setSp_normal(float sp_normal) {
		this.sp_normal = sp_normal;
	}
}
