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

import net.ion.open.oadr2.model.v20b.ei.SignalTypeEnumeratedType;

public class ProgramPeakRate {
	
	private transient String peakRateId;
	private String programId;
	private String name;
	private String type = SignalTypeEnumeratedType.LEVEL.value();
	private StandardSignals.UNIT unit = StandardSignals.UNIT.none;
	private long dtStart;
	private long duration;
	private List<String> resourceIds;
	
	private transient List<ProgramPeakRateInterval> intervals = new ArrayList<ProgramPeakRateInterval>();

	public String getPeakRateId() {
		return peakRateId;
	}

	public void setPeakRateId(String peakRateId) {
		this.peakRateId = peakRateId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public StandardSignals.UNIT getUnit() {
		return unit;
	}

	public void setUnit(StandardSignals.UNIT unit) {
		this.unit = unit;
	}

	public long getDtStart() {
		return dtStart;
	}

	public void setDtStart(long dtStart) {
		this.dtStart = dtStart;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public List<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(List<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public List<ProgramPeakRateInterval> getIntervals() {
		return intervals;
	}

	public void setIntervals(List<ProgramPeakRateInterval> intervals) {
		this.intervals = intervals;
	}

	

}
