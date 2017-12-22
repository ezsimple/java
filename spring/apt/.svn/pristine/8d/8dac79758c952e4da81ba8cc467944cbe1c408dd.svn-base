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


public class Program {
	private transient String programId;
	private String name;
	private String marketContext;
	private String description;
	private long created = System.currentTimeMillis();
	private String createdBy;
	private String programType;
	private Boolean isBaseline;
	private Boolean isPeakRate;
	private ProgramBaseline baseline;
	private ProgramPeakRate peakRate;
	private transient String createdDisplay;
	
	private String eventNotificationType;
	private int maxNumEvents;
	private int minNumOptInResponses;
	private String optTypes;
	private Boolean optOutPenalty;
	private Float penaltyRate;

	public Program(){}


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

	public String getMarketContext() {
		return marketContext;
	}

	public void setMarketContext(String marketContext) {
		this.marketContext = marketContext;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getCreatedDisplay() {
		return ISOUtils.format(getCreated());
	}


	public String getProgramType() {
		return programType;
	}


	public void setProgramType(String programType) {
		this.programType = programType;
	}


	public Boolean getIsBaseline() {
		return isBaseline;
	}


	public void setIsBaseline(Boolean isBaseline) {
		this.isBaseline = isBaseline;
	}


	public Boolean getIsPeakRate() {
		return isPeakRate;
	}


	public void setIsPeakRate(Boolean isPeakRate) {
		this.isPeakRate = isPeakRate;
	}


	public ProgramBaseline getBaseline() {
		return baseline;
	}


	public void setBaseline(ProgramBaseline baseline) {
		this.baseline = baseline;
	}


	public ProgramPeakRate getPeakRate() {
		return peakRate;
	}


	public void setPeakRate(ProgramPeakRate peakRate) {
		this.peakRate = peakRate;
	}


	public String getEventNotificationType() {
		return eventNotificationType;
	}


	public void setEventNotificationType(String eventNotificationType) {
		this.eventNotificationType = eventNotificationType;
	}


	public int getMaxNumEvents() {
		return maxNumEvents;
	}


	public void setMaxNumEvents(int maxNumEvents) {
		this.maxNumEvents = maxNumEvents;
	}


	public int getMinNumOptInResponses() {
		return minNumOptInResponses;
	}


	public void setMinNumOptInResponses(int minNumOptInResponses) {
		this.minNumOptInResponses = minNumOptInResponses;
	}


	public String getOptTypes() {
		return optTypes;
	}


	public void setOptTypes(String optTypes) {
		this.optTypes = optTypes;
	}


	public Boolean getOptOutPenalty() {
		return optOutPenalty;
	}


	public void setOptOutPenalty(Boolean optOutPenalty) {
		this.optOutPenalty = optOutPenalty;
	}


	public Float getPenaltyRate() {
		return penaltyRate;
	}


	public void setPenaltyRate(Float penaltyRate) {
		this.penaltyRate = penaltyRate;
	}


	public void setCreatedDisplay(String createdDisplay) {
		this.createdDisplay = createdDisplay;
	}

	
}
