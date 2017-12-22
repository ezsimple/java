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


public class EventSignal {

	private transient String signalId;
	private String eventId;
	private String name;
//	private SignalTypeEnumeratedType type = SignalTypeEnumeratedType.LEVEL;
	private String type = SignalTypeEnumeratedType.LEVEL.value();
	private StandardSignals.UNIT unit = StandardSignals.UNIT.none;
	private float currentValue;

	private transient List<EventSignalInterval> intervals = new ArrayList<EventSignalInterval>();

	public EventSignal(){

	}

	public String getSignalId() {
		return signalId;
	}

	public void setSignalId(String signalId) {
		this.signalId = signalId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
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

	public List<EventSignalInterval> getIntervals() {
		return intervals;
	}

	public void setIntervals(List<EventSignalInterval> intervals) {
		this.intervals = intervals;
	}

	public float getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(float currentValue) {
		this.currentValue = currentValue;
	}

	public StandardSignals.UNIT getUnit() {
		return unit;
	}

	public void setUnit(StandardSignals.UNIT unit) {
		this.unit = unit;
	}


}
